# Instaclone

## Persone

Nome | Ruolo | Note 
-----|-------|------
Bettin Michele | Frontend developer | mail@mail.com
Sinigaglia Gianluca | Backend developer | g.sinigaglia@outlook.it

## Funzionalità dell'applicazione

L'applicazione vuole riproporre le funzionalità principali del social network Instagram.

- Gestione utenti nell'applicazione
- Gestione login utenti, anche tramite oauth2 (google)
- Upload e gestione foto (like, thread di commenti)
- Upload e gestione storie
- Gestione follower / followed
- Bacheca utente con feedback dei followed
- Messaggistica tra utenti
- Statistiche dell'applicazione (utente admin)

## Database
Postgres 16

Convenzioni suffissi: 
- _T tabella contentente dati applicativi
- _R tabella di relazione
- _D tabella di dominio

```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE audit_logs (
    audit_log_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    action VARCHAR(255) NOT NULL,
    table_name VARCHAR(255) NOT NULL,
    record_id UUID NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_audit_logs_user ON audit_logs (user_id);
CREATE INDEX idx_audit_logs_insert_timestamp ON audit_logs (insert_timestamp);

CREATE TABLE roles (
    role_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    description VARCHAR(50) NOT NULL UNIQUE,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO roles (description) VALUES
    ('ADMIN'),
    ('USER');

CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx__users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);

CREATE TABLE users_roles (
    user_role_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE INDEX idx_user_roles_user_id ON users_roles (user_id);
CREATE INDEX idx_users_rolesole_id ON users_roles (role_id);

CREATE TABLE profiles (
    profile_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID UNIQUE NOT NULL,
    bio TEXT,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE INDEX idx_profiles_user_id ON profiles (user_id);

CREATE TABLE pictures_types (
    picture_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    description VARCHAR(100) NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO pictures_types (description) VALUES
    ('AVATAR'),
    ('POST'),
    ('STORY');

CREATE TABLE pictures (
    picture_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    profile_id UUID NOT NULL,
    picture_type_id UUID NOT NULL,
    picture_data BYTEA NOT NULL CHECK (octet_length(picture_data) <= 5242880), -- 5 MB in byte,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id),
    FOREIGN KEY (picture_type_id) REFERENCES pictures_types(picture_type_id)
);

CREATE INDEX idx_pictures_profile_id ON pictures (profile_id);
CREATE INDEX idx_pictures_picture_type ON pictures (picture_type_id);

CREATE TABLE comments (
    comment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    picture_id UUID NOT NULL,
    commenter_id UUID NOT NULL,
    parent_comment_id UUID, -- Riferimento al commento padre, se esiste
    comment_text TEXT NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (picture_id) REFERENCES pictures (picture_id),
    FOREIGN KEY (commenter_id) REFERENCES profiles (profile_id),
    FOREIGN KEY (parent_comment_id) REFERENCES comments (comment_id)
);

CREATE INDEX idx_comments_picture_id ON comments (picture_id);
CREATE INDEX idx_comments_commenter_id ON comments (commenter_id);
CREATE INDEX idx_comments_parent_comment_id ON comments (parent_comment_id);

CREATE TABLE likes (
    like_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    picture_id UUID NOT NULL,
    profile_id UUID NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (picture_id, profile_id), -- Vincolo per un solo like a foto per profilo
    FOREIGN KEY (picture_id) REFERENCES pictures(picture_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id)
);

CREATE INDEX idx_likes_picture_id ON likes (picture_id);
CREATE INDEX idx_likes_profile_id ON likes (profile_id);

CREATE TABLE messages (
    message_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    sender_id UUID NOT NULL,
    receiver_id UUID NOT NULL,
    message_text TEXT NOT NULL,
    insert_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES profiles(profile_id),
    FOREIGN KEY (receiver_id) REFERENCES profiles(profile_id)
);

CREATE INDEX idx_messages_sender_id ON messages (sender_id);
CREATE INDEX idx_messages_receiver_id ON messages (receiver_id);

```

## Docker

```bash
docker pull postgres
docker volume create pgdata
docker run --name postgres-container -e POSTGRES_PASSWORD=password -d -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres

```