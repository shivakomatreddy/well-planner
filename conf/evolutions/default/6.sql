-- Project Tasks Schema

# --- !Ups
CREATE TABLE "tasks" ("id" SERIAL PRIMARY KEY, "title" varchar(1024), "description" varchar(1024), "notes" varchar(1024), "isCategory" boolean, "isVisibleToCustomer"  boolean, "dueDate" timestamp, "assignedUserId" SERIAL, "userCreatedID" SERIAL, "businessId" SERIAL, "projectId" SERIAL,
"parentTaskId" SERIAL, FOREIGN KEY("assignedUserId") REFERENCES "users"(id), FOREIGN KEY("userCreatedID") REFERENCES "users"(id), FOREIGN KEY("projectId") REFERENCES "projects"(id), FOREIGN KEY("businessId") REFERENCES "businesses"(id), "modifiedDate" timestamp, "createdDate" timestamp);

# --- !Downs
DROP TABLE "tasks";