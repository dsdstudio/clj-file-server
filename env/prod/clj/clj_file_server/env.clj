(ns clj-file-server.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[clj-file-server started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-file-server has shut down successfully]=-"))
   :middleware identity})
