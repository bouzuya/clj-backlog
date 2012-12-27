(ns backlog.api.admin
  [:require [backlog.util :as util]])

(defn get-users
  []
  (util/call :backlog.admin.getUsers))

(defn add-user
  [user-id password-md5 name mail-address role & {:as opts}]
  (util/call :backlog.admin.addUser
    (into {} (remove (comp nil? val)
                     (merge {:user_id user-id
                             :password_md5 password-md5
                             :name name
                             :mail_address mail-address
                             :role role}
                            (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn update-user
  [id & {:as opts}]
  (util/call :backlog.admin.updateUser
             (into {} (remove (comp nil? val)
                              (merge {:id id}
                                     (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn delete-user
  [id]
  (util/call :backlog.admin.deleteUser id))

(defn get-projects
  []
  (util/call :backlog.admin.getProjects))

(defn add-project
  [name key & {:as opts}]
  (util/call :backlog.admin.addProject
             (into {} (remove (comp nil? val)
                              (merge {:name name
                                      :key key}
                                     (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn update-project
  [id & {:as opts}]
  (util/call :backlog.admin.updateProject
             (into {} (remove (comp nil? val)
                              (merge {:id id}
                                     (into {} (map (fn [[k v]] [(util/backlog-keyword k) v]) opts)))))))

(defn delete-project
  [id]
  (util/call :backlog.admin.deleteProject {:id id}))

(defn get-project-users
  [project-id]
  (util/call :backlog.admin.getProjectUsers project-id))

(defn add-project-user
  [project-id user-id]
  (util/call :backlog.admin.addProjectUser
             {:project_id project-id :user_id user-id}))

(defn update-project-users
  [project-id user-id]
  (util/call :backlog.admin.updateProjectUsers
             {:project_id project-id
              :user_id user-id}))

; TODO: BAPI-57
(defn delete-project-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-58
(defn add-custom-field
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-59
(defn updadte-custom-field
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-60
(defn delete-custom-field
  []
  (throw (UnsupportedOperationException.)))

