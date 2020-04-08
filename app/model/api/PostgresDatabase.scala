package model.api

import anorm.{Macro, RowParser}
import javax.inject.Inject
import model.api.businesses.Business
import model.api.users.User
import play.api.db.DBApi

@javax.inject.Singleton
class PostgresDatabase @Inject() (dbApi: DBApi) {

  protected val db = dbApi.database("default")



}
