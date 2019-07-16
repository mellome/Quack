package de.quackr.persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Date> date;
	public static volatile SingularAttribute<Comment, User> author;
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, String> text;
	public static volatile SingularAttribute<Comment, Long> qid;

	public static final String DATE = "date";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String QID = "qid";

}

