package com.movilepay.third.party.ldap.repository

import com.movilepay.business.model.User
import com.movilepay.business.repository.UserStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.core.support.BaseLdapNameAware
import org.springframework.stereotype.Service
import javax.naming.ldap.LdapName
import org.springframework.ldap.support.LdapNameBuilder
import javax.naming.Name
import javax.naming.directory.Attributes
import javax.naming.directory.BasicAttribute
import javax.naming.directory.BasicAttributes

@Service
class LdapUserRepository(
    private val ldapTemplate: LdapTemplate
) : BaseLdapNameAware, UserStoreRepository {

    @Autowired
    private var baseLdapPath: LdapName? = null

    override fun create(user: User) {
        val dn = buildDn(user.id)
        ldapTemplate.bind(dn, null, buildAttributes(user))
    }

    override fun update(user: User) {
        val dn = buildDn(user.id)
        ldapTemplate.rebind(dn, null, buildAttributes(user))
    }

    override fun delete(userId: String) {
        val dn = buildDn(userId)
        ldapTemplate.unbind(dn)
    }

    override fun setBaseLdapPath(p0: LdapName?) {
        this.baseLdapPath = baseLdapPath;
    }

    private fun buildDn(userId: String): Name {
        return LdapNameBuilder.newInstance(baseLdapPath).add("uid", userId).build();
//        return LdapNameBuilder.newInstance(baseLdapPath)
//            .add("cn", "${p.firstName} ${p.lastName}")
//            .build()
    }

    private fun buildAttributes(p: User): Attributes {
        val attrs = BasicAttributes()
        val ocAttr = BasicAttribute("objectClass")
        ocAttr.add("top")
        ocAttr.add("inetOrgPerson")
        attrs.put(ocAttr)
        attrs.put("uid", p.id)
        attrs.put("cn", "${p.firstName} ${p.lastName}")
        attrs.put("sn", p.lastName)
        attrs.put("mail", p.email)
        return attrs
    }
}