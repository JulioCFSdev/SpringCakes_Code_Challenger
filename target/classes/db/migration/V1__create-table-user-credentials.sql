CREATE TABLE user_credentials (
    user_credentials_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    cpf TEXT NOT NULL UNIQUE,
    phone_number TEXT NOT NULL,
    status TEXT NOT NULL,
    role TEXT NOT NULL
);