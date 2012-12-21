(ns backlog.util
  [:require [backlog.case :as case]])

(def backlog-snake-case-keywords
  #{:created-on-min
    :created-on-max
    :updated-on-min
    :updated-on-max
    :start-date-min
    :start-date-max
    :due-date-min
    :due-date-max
    :custom-fields})

(defn backlog-keyword
  [k]
  (let [normalized (case/normalized-keyword k)
        normalize (if (backlog-snake-case-keywords normalized)
                    case/snake-keyword
                    case/camel-keyword)]
    (normalize k)))

