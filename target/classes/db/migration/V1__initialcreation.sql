CREATE TABLE customer
(
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
)

CREATE TABLE bank_account
(
    id bigint NOT NULL,
    bank_name character varying(255),
    iban character varying(255),
    id_customer bigint NOT NULL,
    CONSTRAINT bank_account_pkey PRIMARY KEY (id),
    CONSTRAINT bank_account_to_customer_fk FOREIGN KEY (id_customer) REFERENCES customer (id)
)




