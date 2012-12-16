(ns backlog.core
  [:require [backlog.api :as backlog]])

(defn -main
  [& args]
  (if (< (count args) 3)
    (prn "Usage: spacename username password issuekey comment")
    (binding [backlog/*backlog-auth*
              {:spacename (nth args 0)
               :username (nth args 1)
               :password (nth args 2)}]
      (backlog/add-comment (nth args 3) (nth args 4)))))

