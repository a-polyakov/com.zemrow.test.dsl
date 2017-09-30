package com.zemrow.test.dsl.querydsl.service;

import com.zemrow.test.dsl.querydsl.dao.AbstractDao;

/**
 * Универсальный сервис
 * по умолчанию зарегестрирован только метод ACTION_SELECT
 * остальные имеют дефолтную реализацию, но не зарегистрированы (Rout не сможет к ним обратиться)
 * <p>
 * Created on 16.03.2017.
 *
 * @author Alexandr Polyakov
 */
public abstract class AbstractService<D extends AbstractDao> {
//    public static final String ACTION_SELECT = "select";
//    public static final String ACTION_SELECT_BY_ID = "select_by_id";
//    public static final String ACTION_INSERT = "insert";
//    public static final String ACTION_UPDATE = "update";
//    public static final String ACTION_DELETE = "delete";
//    protected final HashMap<String, ServiceAction> registeredMethods;
//
//    protected AbstractService() {
//        registeredMethods = new HashMap<>();
//        String paramJson;
//        try {
//            paramJson = new JSONObject(getDao().getEntityClass().newInstance()).toString();
//        } catch (Exception ignore) {
//            paramJson = "Формат не определенн";
//        }
//        //TODO убрать регистрацию методов по умолчанию
//        registeredMethods.put(ACTION_SELECT, new ServiceAction(
//                this::select,
//                "Получение списока записей",
//                "Условие выборки данных в формате " +
//                        "{" +
//                        "    where:{delete_time:[{relation:\"is_null\", value:true}]}, " +
//                        "    order:[{name:\"asc\"},{message_create_time:\"desc\"}]," +
//                        "    " + ProjectConst.OFFSET + ":20," +
//                        "    " + ProjectConst.LIMIT + ":20" +
//                        "}",
//                "{data:[" + paramJson + ", ...], " + ProjectConst.OFFSET + ":20, " + ProjectConst.LIMIT + ":20} где в data массив найденых объектов"
//        ));
//        registeredMethods.put(ACTION_SELECT_BY_ID, new ServiceAction(
//                this::selectById,
//                "Получить записи по id",
//                "{" + ProjectConst.ID + ":123} для получения информации о конкретной записи ",
//                paramJson
//        ));
//    }
//

    /**
     * DAO для работы сервиса по умолчанию, необходимо реализовывать в классе потомке
     *
     * @return
     */
    protected abstract D getDao();

//    /**
//     * обработать запрос пользователя, передать в один из зарегистрированных методов
//     *
//     * @param action_id
//     * @param session
//     * @param json
//     * @return
//     * @throws IllegalStateException
//     */
//    public final JSONObject call(String action_id, SessionStorage session, JSONObject json) throws IllegalStateException {
//        JSONObject result = null;
//        final ServiceAction serviceAction = registeredMethods.get(action_id);
//        if (serviceAction != null) {
//            final BiFunction<SessionStorage, JSONObject, JSONObject> f = serviceAction.function;
//            result = f.apply(session, json);
//            if (result.opt(ProjectConst.SCOPE) == null) {
//                result.put(ProjectConst.SCOPE, serviceAction.scope);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * Получить entity по id
//     *
//     * @param session
//     * @param json
//     * @return
//     */
//    protected JSONObject selectById(SessionStorage session, JSONObject json) {
//        try {
//            final long id = json.getLong(ProjectConst.ID);
//            final AbstractEntity entity = getDao().select(session, id);
//            return new JSONObject(entity);
//        } catch (JSONException e) {
//            return null;
//        }
//    }
//
//    /**
//     * Получить список записей
//     *
//     * @param session
//     * @param json
//     * @return
//     */
//    protected JSONObject select(SessionStorage session, JSONObject json) {
//        // Определить доступный набор полей
//        final HashMap<String, Path> map = DaoUtils.columnToMap(getDao().getSqlPath());
//
//        //where
//        final List<Predicate> predicateList = DaoUtils.where(map, json);
//        Predicate[] predicates = null;
//        if (predicateList.size() > 0) {
//            predicates = predicateList.toArray(new Predicate[predicateList.size()]);
//        }
//
//        // order
//        final List<OrderSpecifier> orderSpecifierList = DaoUtils.order(map, json);
//        final SimpleExpression idPath = getDao().getIdPath();
//        if (idPath instanceof ComparableExpressionBase) {
//            orderSpecifierList.add(((ComparableExpressionBase) idPath).asc());
//        }
//        final OrderSpecifier[] order = orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);
//
//        long offset = json.optLong(ProjectConst.OFFSET, ProjectConst.OFFSET_DEFAULT);
//        long limit = json.optLong(ProjectConst.LIMIT, ProjectConst.LIMIT_DEFAULT);
//
//        final List list = getDao().select(session, predicates, order, offset, limit);
//        final JSONObject jsonObject = new JSONObject();
//        jsonObject.put(ProjectConst.DATA, list);
//        jsonObject.put(ProjectConst.OFFSET, offset);
//        jsonObject.put(ProjectConst.LIMIT, limit);
//        return jsonObject;
//    }
//
//    /**
//     * Добавление записи
//     *
//     * @param session
//     * @param json
//     * @return
//     */
//    protected JSONObject insert(SessionStorage session, JSONObject json) {
//        try {
//            final AbstractEntity entity = (AbstractEntity) getDao().getEntityClass().newInstance();
//            json.jsonToObject(entity);
//            getDao().insert(session, entity);
//            session.connection.commit();
//            return new JSONObject(entity);
//        } catch (Exception e) {
//            throw new MessengerException(e, MessageEnum.CRUD__INSERT_ERROR, session.locale, getDao().getSqlPath().getTableName());
//        }
//    }
//
//    /**
//     * Обновление записи
//     *
//     * @param session
//     * @param json
//     * @return
//     */
//    protected JSONObject update(SessionStorage session, JSONObject json) {
//        try {
//            final AbstractEntity entity = getDao().select(session, json.getLong(ProjectConst.ID));
//            json.jsonToObject(entity);
//            getDao().update(session, entity);
//            session.connection.commit();
//            return new JSONObject(entity);
//        } catch (Exception e) {
//            throw new MessengerException(e, MessageEnum.CRUD__UPDATE_ERROR, session.locale, getDao().getSqlPath().getTableName());
//        }
//    }
//
//    /**
//     * Пометить запись как удаленная
//     *
//     * @param session
//     * @param json
//     * @return
//     */
//    protected JSONObject delete(SessionStorage session, JSONObject json) {
//        try {
//            long id = json.getLong(ProjectConst.ID);
//            getDao().markAsDeleted(session, id);
//            session.connection.commit();
//            return new JSONObject(getDao().select(session, id));
//        } catch (Exception e) {
//            throw new MessengerException(e, MessageEnum.CRUD__DELETE_ERROR, session.locale, getDao().getSqlPath().getTableName());
//        }
//    }
//
//    public JSONObject api() {
//        final JSONObject json = new JSONObject();
//        for (Map.Entry<String, ServiceAction> entry : registeredMethods.entrySet()) {
//            json.put(entry.getKey(), entry.getValue().api());
//        }
//        return json;
//    }
}
