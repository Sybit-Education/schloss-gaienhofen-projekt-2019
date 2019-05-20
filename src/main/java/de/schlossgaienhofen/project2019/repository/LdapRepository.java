package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.User;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Repository to connect on LDAP.
 */
@Repository
public class LdapRepository {

   private final LdapTemplate ldapTemplate;
   private static final Integer THREE_SECONDS = 3000;

   public LdapRepository(LdapTemplate template) {
      this.ldapTemplate = template;
   }

   /**
    * Get User of LDAP by given token.
    * The Token is mapped in LDAP to uid.
    *
    * @param token
    * @return
    */
   public User getUserByToken(String token) {

      LdapQuery query = query()
         .searchScope(SearchScope.SUBTREE)
         .timeLimit(THREE_SECONDS)
         .countLimit(10)
         .attributes("cn", "sAMAccountName")
         .base(LdapUtils.emptyLdapName())
         .where("objectclass").is("person")
         .and("sAMAccountName").is(token);

      List<User> userList = ldapTemplate.search(query, new UserAttributesMapper());

      User result;
      if (userList.size() > 1) {
         throw new InternalError("Result should be just one!");
      } else if (userList.size() == 0) {
         result = null;
      } else {
         result = userList.get(0);
      }

      return result;
   }

   /**
    * Custom user attributes mapper, maps the attributes to the user vo.
    */
   private class UserAttributesMapper implements AttributesMapper<User> {

      public User mapFromAttributes(final Attributes attrs) throws NamingException {
         final User user = new User();
         //TODO: user.setUserToken((String) attrs.get("sAMAccountName").get());
         //TODO: user.setFullName((String) attrs.get("cn").get());

         return user;
      }


   }
}
