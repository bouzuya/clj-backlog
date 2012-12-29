(ns backlog.config)

(def ^:dynamic *backlog-auth*
  (zipmap [:spacename :username :password] (repeat nil)))


