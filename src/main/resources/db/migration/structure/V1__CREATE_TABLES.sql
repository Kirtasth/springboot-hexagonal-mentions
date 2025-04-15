CREATE TABLE courses
(
    course_id    BIGINT NOT NULL,
    name         VARCHAR(255) NULL,
    course_start date NULL,
    day_of_week  SMALLINT NULL,
    start_hour   time NULL,
    end_hour     time NULL,
    created_at   datetime NULL,
    updated_at   datetime NULL,
    CONSTRAINT pk_courses PRIMARY KEY (course_id)
);

CREATE TABLE given_classes
(
    given_class_id BIGINT       NOT NULL,
    meet_link      VARCHAR(255) NOT NULL,
    material_link  VARCHAR(255) NOT NULL,
    professor_id   BIGINT       NOT NULL,
    course_id      BIGINT       NOT NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    CONSTRAINT pk_given_classes PRIMARY KEY (given_class_id)
);

CREATE TABLE professors
(
    professor_id   BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    discord_name   VARCHAR(255) NULL,
    professor_role VARCHAR(255) NULL,
    created_at     datetime NULL,
    updated_at     datetime NULL,
    CONSTRAINT pk_professors PRIMARY KEY (professor_id)
);

CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                      BIGINT AUTO_INCREMENT NOT NULL,
    username                VARCHAR(255) NULL,
    email                   VARCHAR(255) NULL,
    password                VARCHAR(255) NULL,
    enabled                 BIT(1) NOT NULL,
    account_non_expired     BIT(1) NULL,
    account_non_locked      BIT(1) NULL,
    credentials_non_expired BIT(1) NULL,
    created_at              datetime NULL,
    updated_at              datetime NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE users_roles
    ADD CONSTRAINT uc_7edd5d14ffc6147358a4b7988 UNIQUE (user_id, role_id);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE given_classes
    ADD CONSTRAINT FK_GIVEN_CLASSES_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (course_id);

ALTER TABLE given_classes
    ADD CONSTRAINT FK_GIVEN_CLASSES_ON_PROFESSOR FOREIGN KEY (professor_id) REFERENCES professors (professor_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role_entity FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);