(ns clj-file-server.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [clj-file-server.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[clj-file-server started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-file-server has shut down successfully]=-"))
   :middleware wrap-dev})
