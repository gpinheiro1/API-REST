package com.movilepay.webservice.filter

import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@WebFilter("/")
class CorsFilter : Filter {
    companion object {

        const val ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin"
        const val ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods"
        const val ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers"
        const val ORIGIN = "Origin"
        val allowsDomains = HashSet<String>()
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        var origin = httpServletRequest.getHeader(ORIGIN)

        if (!allowsDomains.isEmpty() && origin != null && !origin.isEmpty()) {
            var locate = false
            for (allow in allowsDomains) {
                if (allow.contains(origin)) {
                    locate = true
                    break
                }
            }
        } else {
            origin = "*"
        }

        // Adiciona os cabecalhos
        response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin)
        response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, DELETE, PUT, OPTIONS")
        response.addHeader("Access-Control-Expose-Headers", "*")
        response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, """
                Content-Type, Access-Control-Allow-Headers, Authorization,
                Access-Control-Allow-Methods, Access-Control-Allow-Origin,
                """.trim())

        httpServletResponse.setHeader("X-Service-Name", "movilepay-merchant-bff")

        chain.doFilter(request, response)
    }

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        //
    }

    override fun destroy() {
        //
    }

}