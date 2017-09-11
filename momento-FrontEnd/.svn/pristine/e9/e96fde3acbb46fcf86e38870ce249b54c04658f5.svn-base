/*
 * Creation : 9 mai 2016
 */
package com.inetpsa.pi201.manageprojects.infrastructure.finders.jpa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.MapUtils;
import org.javatuples.Pair;
import org.seedstack.business.assembler.FluentAssembler;
import org.seedstack.business.finder.BaseRangeFinder;
import org.seedstack.business.finder.Range;
import org.seedstack.business.finder.Result;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;
import com.inetpsa.pi201.manageprojects.rest.user.UserAssembleur;
import com.inetpsa.pi201.manageprojects.rest.user.UserFinder;
import com.inetpsa.pi201.manageprojects.rest.user.UserRepresentation;

public class UserFinderJpa extends BaseRangeFinder<UserRepresentation, Map<String, Object>> implements UserFinder {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FluentAssembler fluentAssembler;

    @Inject
    private UserRepository mUserRepository;

    @Inject
    private UserAssembleur mUserAssembleur;

    private String whereClauseEnd;

    public List<UserRepresentation> findAllUser() {
        List<UserRepresentation> l_listUserRepresentation = new ArrayList<UserRepresentation>();

        List<User> l_listUser = mUserRepository.loadAll();
        if (l_listUser == null | l_listUser.size() == 0) {
            return null;
        }

        for (User l_User : l_listUser) {
            UserRepresentation l_UserRepresentation = new UserRepresentation();

            mUserAssembleur.assembleDtoFromAggregate(l_UserRepresentation, l_User);

            l_listUserRepresentation.add(l_UserRepresentation);
        }

        return l_listUserRepresentation;
    }

    private CriteriaQuery<User> getCriteriaQuery(Map<String, Object> criteria) {
        String searchStringKey = "searchString";

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> customer = q.from(User.class);
        q.select(customer);

        List<Predicate> restrictions = new ArrayList<Predicate>();

        // Check and set searchString
        String searchString = null;
        if (criteria != null) {
            searchString = (String) criteria.get(searchStringKey);
        }

        if (searchString != null) {
            Field[] fields = User.class.getDeclaredFields();
            for (Field field : fields) {
                if (String.class.isAssignableFrom(field.getType())) {
                    restrictions.add(cb.like(cb.upper(customer.<String> get(field.getName())), "%" + searchString.toUpperCase() + "%"));
                }
            }
        }
        if (!restrictions.isEmpty()) {
            q.where(cb.or(restrictions.toArray(new Predicate[restrictions.size()])));
        }
        return q;
    }

    public UserRepresentation findUserbyId(String p_loginUser) {
        UserRepresentation l_UserRepresentation = new UserRepresentation();

        User l_User = mUserRepository.load(p_loginUser);

        if (l_User == null) {
            return null;
        }

        mUserAssembleur.assembleDtoFromAggregate(l_UserRepresentation, l_User);

        return l_UserRepresentation;
    }

    public Result<UserRepresentation> findAllUser(Range p_range, Map<String, Object> p_criteria) {
        // TODO Auto-generated method stub
        return this.find(p_range, p_criteria);
    }

    @Override
    protected List<UserRepresentation> computeResultList(Range range, Map<String, Object> criteria) {

        StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new ");
        queryStr.append(UserRepresentation.class.getName());
        queryStr.append("(u.login, u.firstName, u.name)");
        queryStr.append(" from User u");
        if (getWhereClauseEnd() != null && !getWhereClauseEnd().equals("")) {
            queryStr.append(" where " + getWhereClauseEnd());
        }
        queryStr.append(" order by u.login");
        TypedQuery<UserRepresentation> query = entityManager.createQuery(queryStr.toString(), UserRepresentation.class);

        // TypedQuery<User> query = entityManager.createQuery(getCriteriaQuery(criteria));
        query.setFirstResult((int) range.getOffset());
        query.setMaxResults((int) range.getSize());

        // List<UserRepresentation> result = new ArrayList<UserRepresentation>();
        // for (UserRepresentation customer : query.getResultList()) {
        // result.add(fluentAssembler.assemble((AggregateRoot<?>) customer).to(UserRepresentation.class));
        // }
        //
        // return result;

        return query.getResultList();
    }

    @Override
    protected long computeFullRequestSize(Map<String, Object> criteria) {
        buildWhereClauseEnd("u", criteria);
        TypedQuery<User> query = entityManager.createQuery(getCriteriaQuery(criteria));
        return query.getResultList().size();
    }

    /**
     * Custom where clause end built from criteria map. Note : this is not a reference implementation for building where clause !
     *
     * @param product the product
     * @param criteria the criteria
     */
    private void buildWhereClauseEnd(String user, Map<String, Object> criteria) {

        StringBuilder whereClauseCriteria = new StringBuilder("");
        String fieldSeparator = ".";
        String space = " ";
        String equal = " = ";
        String upperCaseBegin = " upper(";
        String parenthesesEnd = ") ";
        // String andOpenParentheses = " and ( ";
        String andOpenParentheses = " ( ";
        String or = " or ";

        // filter on other fields' content
        if (MapUtils.isNotEmpty(criteria)) {

            Boolean firstClause = true;
            for (Map.Entry<String, Object> entry : criteria.entrySet()) {

                if (firstClause) {
                    whereClauseCriteria.append(andOpenParentheses);
                    firstClause = false;
                } else {
                    whereClauseCriteria.append(or);
                }

                // find string in fields is case insensitive
                if (entry.getValue() instanceof Pair) {
                    String operator = (String) ((Pair) entry.getValue()).getValue0();
                    String value = (String) ((Pair) entry.getValue()).getValue1();
                    whereClauseCriteria.append(upperCaseBegin).append(user).append(fieldSeparator).append(entry.getKey()).append(parenthesesEnd) // field
                            .append(space).append(operator).append(space) // operator
                            .append(upperCaseBegin).append(value).append(parenthesesEnd); // value
                } else {
                    whereClauseCriteria.append(user).append(fieldSeparator).append(entry.getKey()).append(equal).append(entry.getValue());
                }
            }
            // if at least one clause was found, close the parentheses
            if (!firstClause) {
                whereClauseCriteria.append(parenthesesEnd);
            }
        }
        whereClauseEnd = whereClauseCriteria.toString();
    }

    private String getWhereClauseEnd() {
        return whereClauseEnd;
    }

}
