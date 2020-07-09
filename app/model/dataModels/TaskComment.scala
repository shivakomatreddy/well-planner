package model.dataModels

import org.joda.time.DateTime

case class TaskComment(id: Int, commentText: Option[String] = None,
                       userCreatedId: Int, taskId: Int, businessId: Int,
                       projectId: Int, modifiedDate: DateTime,
                       createdDate: DateTime)
