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

(defn count-issue
  [project-id]
  (throw (UnsupportedOperationException.)))

(defn find-issue
  [project-id]
  (throw (UnsupportedOperationException.)))

(defn create-issue
  [project-id summary]
  ; TODO: optional arguments
  (call :backlog.createIssue {:projectId project-id
                              :summary  summary}))

; TODO: BAPI-24
(defn update-issue
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-25
(defn switch-status
  []
  (throw (UnsupportedOperationException.)))

(defn add-comment
  [issue-key content]
  (call :backlog.addComment {:key issue-key :content content}))

(defn add-issue-type
  [project-id name color]
  (call :backlog.addIssueType {:project_id project-id
                               :name name
                               :color color}))

(defn update-issue-type
  [id name color]
  (call :backlog.updateIssueType {:id id
                                  :name name
                                  :color color}))

(defn delete-issue-type
  [id & {:keys [substitute-id]}]
  (call :backlog.deleteIssueType
        (into {} (remove (comp nil? val)
                         {:id id
                          :substitute_id substitute-id}))))

(defn add-version
  [project-id name & {:keys [start-date due-date]}]
  (call :backlog.addVersion
        (into {} (remove (comp nil? val)
                         {:project_id project-id
                          :name name
                          :start_date start-date
                          :due_date due-date}))))

(defn update-version
  [id name & {:keys [start-date due-date archived]}]
  (call :backlog.updateVersion
        (into {} (remove (comp nil? val)
                         {:id id
                          :name name
                          :start_date start-date
                          :due_date due-date
                          :archived archived}))))

(defn delete-version
  [id]
  (call :backlog.deleteVersion id))

; TODO: BAPI-33
(defn add-component
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-34
(defn update-component
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-35
(defn delete-component
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-36
(defn get-timeline
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-37
(defn get-project-summary
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-38
(defn get-project-summaries
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-39
(defn get-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-40
(defn get-user-icon
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-41
(defn get-activity-types
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-42
(defn get-statuses
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-43
(defn get-resolutions
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-44
(defn get-priorities
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-45
(defn get-custom-fields
  []
  (throw (UnsupportedOperationException.)))

