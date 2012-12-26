(ns backlog.api
  [:require [backlog.util :as util]])

(defn get-projects
  []
  (util/call :backlog.getProjects))

(defmulti get-project class)

(defmethod get-project String
  [project-key]
  (util/call :backlog.getProject project-key))

(defmethod get-project Integer
  [project-id]
  (util/call :backlog.getProject project-id))

(defn get-components
  [project-id]
  (util/call :backlog.getComponents project-id))

(defn get-versions
  [project-id]
  (util/call :backlog.getVersions project-id))

(defn get-users
  [project-id]
  (util/call :backlog.getUsers project-id))

(defn get-issue-types
  [project-id]
  (util/call :backlog.getIssueTypes project-id))

(defmulti get-issue class)

(defmethod get-issue String
  [issue-key]
  (util/call :backlog.getIssue issue-key))

(defmethod get-issue Integer
  [issue-id]
  (util/call :backlog.getIssue issue-id))

(defn get-comments
  [issue-id]
  (util/call :backlog.getComments issue-id))

(defn count-issue
  [project-id & {:as opts}]
  (util/call
    :backlog.countIssue
    (into {} (remove (comp nil? val)
                     (merge {:projectId project-id}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn find-issue
  [project-id & {:as opts}]
  (util/call
    :backlog.findIssue
    (into {} (remove (comp nil? val)
                     (merge {:projectId project-id}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn create-issue
  [project-id summary & {:as opts}]
  (util/call :backlog.createIssue
    (into {} (remove (comp nil? val)
                     (merge {:projectId project-id
                             :summary  summary}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn update-issue
  [key & {:as opts}]
  (util/call
    :backlog.updateIssue
    (into {} (remove (comp nil? val)
                     (merge {:key key}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn switch-status
  [key status-id & {:as opts}]
  (util/call
    :backlog.switchStatus
    (into {} (remove (comp nil? val)
                     (merge {:key key :statusId status-id}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn add-comment
  [issue-key content]
  (util/call :backlog.addComment {:key issue-key :content content}))

(defn add-issue-type
  [project-id name color]
  (util/call :backlog.addIssueType {:project_id project-id
                               :name name
                               :color color}))

(defn update-issue-type
  [id name color]
  (util/call :backlog.updateIssueType {:id id
                                  :name name
                                  :color color}))

(defn delete-issue-type
  [id & {:keys [substitute-id]}]
  (util/call :backlog.deleteIssueType
        (into {} (remove (comp nil? val)
                         {:id id
                          :substitute_id substitute-id}))))

(defn add-version
  [project-id name & {:keys [start-date due-date]}]
  (util/call :backlog.addVersion
        (into {} (remove (comp nil? val)
                         {:project_id project-id
                          :name name
                          :start_date start-date
                          :due_date due-date}))))

(defn update-version
  [id name & {:keys [start-date due-date archived]}]
  (util/call :backlog.updateVersion
        (into {} (remove (comp nil? val)
                         {:id id
                          :name name
                          :start_date start-date
                          :due_date due-date
                          :archived archived}))))

(defn delete-version
  [id]
  (util/call :backlog.deleteVersion id))

(defn add-component
  [project-id name]
  (util/call :backlog.addComponent {:project_id project-id
                               :name name}))

(defn update-component
  [id name]
  (util/call :backlog.updateComponent {:id id :name name}))

(defn delete-component
  [id]
  (util/call :backlog.deleteComponent id))

(defn get-timeline
  []
  (util/call :backlog.getTimeline))

(defn get-project-summary
  [project-id]
  (util/call :backlog.getProjectSummary project-id))

(defn get-project-summaries
  []
  (util/call :backlog.getProjectSummaries))

(defmulti get-user class)

(defmethod get-user String
  [id]
  (util/call :backlog.getUser id))

(defmethod get-user Integer
  [id]
  (util/call :backlog.getUser id))

(defn get-user-icon
  [id]
  (util/call :backlog.getUserIcon id))

(defn get-activity-types
  []
  (util/call :backlog.getActivityTypes))

(defn get-statuses
  []
  (util/call :backlog.getStatuses))

(defn get-resolutions
  []
  (util/call :backlog.getResolutions))

(defn get-priorities
  []
  (util/call :backlog.getPriorities))

; TODO: BAPI-45
; NOTE: Max Plan Only
(defn get-custom-fields
  []
  (throw (UnsupportedOperationException.)))

