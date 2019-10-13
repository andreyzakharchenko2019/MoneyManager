CREATE TABLE "users" (
	"id_user" serial NOT NULL,
	"name" varchar(50) NOT NULL,
	"e_mail" varchar(50) NOT NULL UNIQUE,
	"password" varchar(50) NOT NULL,
	"premium" integer NOT NULL DEFAULT '0',
	CONSTRAINT "users_pk" PRIMARY KEY ("id_user")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transaction" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"date" varchar(11) NOT NULL,
	"category" integer NOT NULL,
	"price" integer NOT NULL,
	"wallet" integer NOT NULL,
	"description" varchar(100),
	"type_transaction" integer NOT NULL,
	CONSTRAINT "transaction_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "category" (
	"id" serial NOT NULL,
	"name_category" varchar(50) NOT NULL,
	"id_user" bigint NOT NULL,
	CONSTRAINT "category_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "wallet" (
	"id" serial NOT NULL,
	"name_wallet" varchar(50) NOT NULL,
	"currency" integer NOT NULL,
	"user_id" integer NOT NULL,
	"amount" integer NOT NULL DEFAULT '0',
	CONSTRAINT "wallet_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "currency" (
	"id" serial NOT NULL,
	"name_currency" varchar(50) NOT NULL,
	CONSTRAINT "currency_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "transaction" ADD CONSTRAINT "transaction_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id_user");
ALTER TABLE "transaction" ADD CONSTRAINT "transaction_fk1" FOREIGN KEY ("category") REFERENCES "category"("id");
ALTER TABLE "transaction" ADD CONSTRAINT "transaction_fk2" FOREIGN KEY ("wallet") REFERENCES "wallet"("id");

ALTER TABLE "category" ADD CONSTRAINT "category_fk0" FOREIGN KEY ("id_user") REFERENCES "users"("id_user");

ALTER TABLE "wallet" ADD CONSTRAINT "wallet_fk0" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "wallet" ADD CONSTRAINT "wallet_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id_user");