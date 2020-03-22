-- Project Task Comments Schema

# --- !Ups
CREATE TABLE "taskComments" ("id" SERIAL PRIMARY KEY, "commentText" varchar(1024), "userCreatedID" SERIAL, "taskId" SERIAL, "businessId" SERIAL, "projectId" SERIAL, FOREIGN KEY("taskId") REFERENCES "tasks"(id), FOREIGN KEY("userCreatedID") REFERENCES "users"(id), FOREIGN KEY("projectId") REFERENCES "projects"(id), FOREIGN KEY("businessId") REFERENCES "businesses"(id), "modifiedDate" timestamp, "createdDate" timestamp);

# --- !Downs
DROP TABLE "taskComments";