package model.dataModels

import org.joda.time.DateTime

case class Task(id: Int, title: Option[String] = None, description: Option[String] = None,
                notes: Option[String] = None, isCategory: Option[Boolean] = None, isVisibleToCustomer: Boolean,
                dueDate: DateTime , assignedUserId: Int, userCreatedId: Int, businessId: Int, projectId: Int,
                parentTaskId: Int, modifiedDate: DateTime , createdDate: DateTime)
