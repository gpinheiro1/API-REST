package com.movilepay.jdbc.repository.sql

object JdbcCompanyRepositorySQL {

    const val GET_ALL_COMPANIES = """
        SELECT
            c.id,
            c.name,
            c.email
        FROM company c 
        ORDER BY c.id
        OFFSET(:page * :size)
    """

    const val GET_COMPANY_BY_ID = """
        SELECT 
            c.id,
            c.name,
            c.email
        FROM company c 
        WHERE c.id = :id
    """
    const val INSERT_COMPANY = """
        INSERT INTO company
        (
            id,
            name,
            email
        )
        VALUES
        (
            :id,
            :name,
            :email
        )
    """

    const val UPDATE_COMPANY = """
       UPDATE company SET name = :name, email = :email WHERE id = :id 
    """

    const val DELETE_COMPANY = """
       DELETE FROM company
        WHERE id = :id
    """
}