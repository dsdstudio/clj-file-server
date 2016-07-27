(ns user
  (:require [mount.core :as mount]
            clj-file-server.core))

(defn start []
  (mount/start-without #'clj-file-server.core/repl-server))

(defn stop []
  (mount/stop-except #'clj-file-server.core/repl-server))

(defn restart []
  (stop)
  (start))


