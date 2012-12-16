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

(defn get-project
  [project-key]
  (call :backlog.getProject project-key))

(defn get-issue
  [issue-key]
  (call :backlog.getIssue issue-key))

(defn get-comments
  [issue-id]
  (call :backlog.getComments issue-id))

(defn add-comment
  [issue-key content]
  (call :backlog.addComment {:key issue-key :content content}))

