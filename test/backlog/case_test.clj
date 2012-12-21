(ns backlog.case-test
  [:use clojure.test
        backlog.case])

(deftest normalized-test
  (testing "normalized"
    (are [input expected]
      (= (normalized input) expected)
      "normalized-case" "normalized-case"
      "camelCase" "camel-case"
      "PascalCase" "pascal-case"
      "snake_case" "snake-case"
      "projectId" "project-id"
      "ProjectId" "project-id"
      "project_id" "project-id"
      "ProjectID" "project-id")))

(deftest camel-test
  (testing "camel"
    (are [input expected]
      (= (camel input) expected)
      "normalized-case" "normalizedCase"
      "camelCase" "camelCase"
      "PascalCase" "pascalCase"
      "snake_case" "snakeCase"
      "projectId" "projectId"
      "ProjectId" "projectId"
      "project_id" "projectId"
      "ProjectID" "projectId")))

(deftest pascal-test
  (testing "pascal"
    (are [input expected]
      (= (pascal input) expected)
      "normalized-case" "NormalizedCase"
      "camelCase" "CamelCase"
      "PascalCase" "PascalCase"
      "snake_case" "SnakeCase"
      "projectId" "ProjectId"
      "ProjectId" "ProjectId"
      "project_id" "ProjectId"
      "ProjectID" "ProjectId")))

(deftest snake-test
  (testing "snake"
    (are [input expected]
      (= (snake input) expected)
      "normalized-case" "normalized_case"
      "camelCase" "camel_case"
      "PascalCase" "pascal_case"
      "snake_case" "snake_case"
      "projectId" "project_id"
      "ProjectId" "project_id"
      "project_id" "project_id"
      "ProjectID" "project_id")))

(deftest normalized-keyword-test
  (testing "normalize-keyword"
    (are [input expected]
      (= (normalized-keyword input) expected)
      :normalized-case :normalized-case
      :camelCase :camel-case
      :PascalCase :pascal-case
      :snake_case :snake-case
      :projectId :project-id
      :ProjectId :project-id
      :project_id :project-id
      :ProjectID :project-id)))

(deftest camel-keyword-test
  (testing "camel-keyword"
    (are [input expected]
      (= (camel-keyword input) expected)
      :normalized-case :normalizedCase
      :camelCase :camelCase
      :PascalCase :pascalCase
      :snake_case :snakeCase
      :projectId :projectId
      :ProjectId :projectId
      :project_id :projectId
      :ProjectID :projectId)))

(deftest pascal-keyword-test
  (testing "pascal-keyword"
    (are [input expected]
      (= (pascal-keyword input) expected)
      :normalized-case :NormalizedCase
      :camelCase :CamelCase
      :PascalCase :PascalCase
      :snake_case :SnakeCase
      :projectId :ProjectId
      :ProjectId :ProjectId
      :project_id :ProjectId
      :ProjectID :ProjectId)))

(deftest snake-keyword-test
  (testing "snake-keyword"
    (are [input expected]
      (= (snake-keyword input) expected)
      :normalized-case :normalized_case
      :camelCase :camel_case
      :PascalCase :pascal_case
      :snake_case :snake_case
      :projectId :project_id
      :ProjectId :project_id
      :project_id :project_id
      :ProjectID :project_id)))

