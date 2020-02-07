package com.movilepay.jdbc.repository.sql

object JdbcAddressRepositorySQL {
    const val GET_BY_USER = """
       SELECT 
            a.id,
            a.country,
            a.state,
            a.city,
            a.zip_code,
            a.street,
            a.number,
            a.user_id
        FROM address a
        WHERE
            a.user_id = :user_id 
    """

    const val INSERT_ADDRESS = """
        INSERT INTO address
        (
            id, 
            country, 
            state, 
            city, 
            zip_code, 
            street, 
            "number", 
            user_id
        )
        VALUES
        (
            :id,
            :country,
            :state,
            :city,
            :zip_code,
            :street,
            :number,
            :user_id
        )
    """
    const val UPDATE_ADDRESS = """
        UPDATE address 
        SET
           country = :country,
           state = :state,
           city = :city,
           zip_code = :zip_code,
           street = :street,
           "number" = :number 
        WHERE
            user_id = :user_id
    """

    const val DELETE_BY_USER = """
       DELETE FROM address 
       WHERE user_id = :user_id
    """
}