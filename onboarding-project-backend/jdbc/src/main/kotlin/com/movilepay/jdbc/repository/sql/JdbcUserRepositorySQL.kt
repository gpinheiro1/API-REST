package com.movilepay.jdbc.repository.sql

object JdbcUserRepositorySQL {
    const val GET_USERS = """
       SELECT
            u.id,
            u.first_name,
            u.last_name,
            u.email,
            u.company,
            u.birth_date,
            u.document_id,
            u.type,
            a.city,
            a.id,
            a.country,
            a.state,
            a.zip_code,
            a.street,
            a.number,
            a.user_id
        FROM users u
        INNER JOIN address a
        ON (u.id = a.user_id) 
        ORDER BY u.id
        OFFSET (:page * :size)
        LIMIT :size
    """


    const val INSERT_USER = """
        INSERT INTO users
        (
            id, 
            first_name, 
            last_name, 
            email, 
            company, 
            birth_date, 
            document_id, 
            "type"
        )
        VALUES 
        (
            :id,
            :first_name,
            :last_name,
            :email,
            :company,
            :birth_date,
            :document_id,
            :type
        )
    """
    const val UPDATE_USER = """
        UPDATE users
        SET 
            first_name = :first_name,
            last_name = :last_name,
            email = :email,
            company = :company,
            birth_date = :birth_date,
            document_id = :document_id,
            type = :type
        WHERE
            id = :id
    """

    const val DELETE_USER = """
        DELETE FROM users 
        WHERE id = :id 
    """
    const val GET_USER_DETAIL = """
       SELECT
            u.id,
            u.first_name,
            u.last_name,
            u.email,
            u.company,
            u.birth_date,
            u.document_id,
            u.type,
            a.city,
            a.id,
            a.country,
            a.state,
            a.zip_code,
            a.street,
            a.number,
            a.user_id
        FROM users u
        INNER JOIN address a
        ON (u.id = a.user_id)
        WHERE u.id = :id
    """
}