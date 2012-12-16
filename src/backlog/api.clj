(ns backlog.api
  [:require [necessary-evil.core :as xml-rpc]])

(declare ^:dynamic *backlog-auth*)

(defn- call
  [methodname & args]
  (let [auth *backlog-auth*]
    (xml-rpc/call*
      (str "https://" (auth :spacename) ".backlog.jp/XML-RPC")
      methodname
      args
      :request {:basic-auth [(auth :username)
                             (auth :password)]})))

; FIXME:
;(defn get-projects
;  []
;  (call (get-auth) :backlog.getProjects))

(defmulti get-project class)

(defmethod get-project String
  [project-key]
  (call :backlog.getProject project-key))

(defmethod get-project Integer
  [project-id]
  (call :backlog.getProject project-id))

(defn get-components
  [project-id]
  (call :backlog.getComponents project-id))

(defn get-versions
  [project-id]
  (call :backlog.getVersions project-id))

(defn get-users
  [project-id]
  (call :backlog.getUsers project-id))

(defn get-issue-types
  [project-id]
  (call :backlog.getIssueTypes project-id))

(defmulti get-issue class)

(defmethod get-issue String
  [issue-key]
  (call :backlog.getIssue issue-key))

(defmethod get-issue Integer
  [issue-id]
  (call :backlog.getIssue issue-id))

(defn get-comments
  [issue-id]
  (call :backlog.getComments issue-id))

(defn add-comment
  [issue-key content]
  (call :backlog.addComment {:key issue-key :content content}))

