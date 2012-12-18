(ns backlog.api
  [:require
   [clj-http.client :as http]
   [necessary-evil.core :as xml-rpc]
   [necessary-evil.methodcall :as mc]
   [necessary-evil.methodresponse :as mr]
   [necessary-evil.xml-utils :as xu]])

(declare ^:dynamic *backlog-auth*)

;;; FIXME: this code is necessary-evil's code.
(defn- call
  [method-name & args]
  (io! "XML-RPC in transaction"
   (let [xml (mc/unparse (mc/methodcall method-name args))
         unparsed (assoc xml :content
                         (conj (xml :content)
                               {:tag :params :attrs nil :content []}))
         body (-> unparsed xu/emit with-out-str)
         auth *backlog-auth*
         post-params {:content-type "text/xml;charset=UTF-8"
                      :basic-auth [(auth :username) (auth :password)]}
         endpoint-url (str "https://" (auth :spacename) ".backlog.jp/XML-RPC")
         response (http/post endpoint-url (assoc post-params :body body))]
     (-> response :body xu/to-xml mr/parse))))

(defn get-projects
  []
  (call :backlog.getProjects))

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

(defn add-component
  [project-id name]
  (call :backlog.addComponent {:project_id project-id
                               :name name}))

(defn update-component
  [id name]
  (call :backlog.updateComponent {:id id :name name}))

(defn delete-component
  [id]
  (call :backlog.deleteComponent id))

; FIXME: BAPI-36
;(defn get-timeline
;  []
;  (call :backlog.getTimeline))

(defn get-project-summary
  [project-id]
  (call :backlog.getProjectSummary project-id))

; FIXME: BAPI-38
;(defn get-project-summaries
;  []
;  (call :backlog.getProjectSummaries))

; TODO: BAPI-39
(defmulti get-user class)

(defmethod get-user String
  [id]
  (call :backlog.getUser id))

(defmethod get-user Integer
  [id]
  (call :backlog.getUser id))

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

