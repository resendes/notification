CREATE TABLE public.notification_scheduling (
	id int8 NOT NULL,
	message varchar(255) NULL,
	recipient varchar(255) NULL,
	scheduling_date timestamp NULL,
	status varchar(255) NULL,
	"type" varchar(255) NULL,
	CONSTRAINT notification_scheduling_pkey PRIMARY KEY (id)
);