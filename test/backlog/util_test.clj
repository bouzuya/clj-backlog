(ns backlog.util-test
  [:use clojure.test
        backlog.util])

(deftest backlog-keyword-test
  (testing "backlog-keyword"
    (are [input expected]
      (= (backlog-keyword input) expected)
      :project-id :projectId
      :created-on-max :created_on_max)))

