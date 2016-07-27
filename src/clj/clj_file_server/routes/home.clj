(ns clj-file-server.routes.home
  (:use compojure.core)
  (:require [clj-file-server.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :refer [redirect file-response]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io])
  (:import [java.io File FileInputStream FileOutputStream]))
(def resource-path "/Users/bhkim/github/clj-file-server/upload/")

(defn home-page []
  (layout/render
    "home.html" {:files (.list (io/file resource-path))}))

(defn file-path [path & [filename]]
  (java.net.URLDecoder/decode
   (str path File/separator filename) "utf-8"))

(defn upload-file
  [path {:keys [tempfile size filename]}]
  (try
    (with-open [in (new FileInputStream tempfile)
                out (new FileOutputStream (file-path path filename))]
      (let [source (.getChannel in)
            dest (.getChannel out)]
        (.transferFrom dest source 0 (.size source))
        (.flush out)))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/upload" [file]
        (upload-file resource-path file)
        (redirect "/"))
  (GET "/files/:filename" [filename]
       (file-response (str resource-path filename))))

