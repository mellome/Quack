package de.quackr.persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Date> birthday;
	public static volatile SingularAttribute<User, String> realName;
	public static volatile SingularAttribute<User, Boolean> moderator;
	public static volatile SingularAttribute<User, Boolean> admin;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Date> signUpTimestamp;
	public static volatile SingularAttribute<User, Date> lastActiveTimestamp;

	public static final String BIRTHDAY = "birthday";
	public static final String REAL_NAME = "realName";
	public static final String MODERATOR = "moderator";
	public static final String ADMIN = "admin";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String PASSWORD_HASH = "passwordHash";
	public static final String USERNAME = "username";
	public static final String SIGN_UP_TIMESTAMP = "signUpTimestamp";
	public static final String LAST_ACTIVE_TIMESTAMP = "lastActiveTimestamp";

}

