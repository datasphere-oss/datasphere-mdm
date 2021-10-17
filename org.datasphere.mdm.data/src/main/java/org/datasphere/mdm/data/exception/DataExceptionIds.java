

package org.datasphere.mdm.data.exception;

import org.datasphere.mdm.system.exception.ExceptionId;

/**
 * @author Mikhail Mikhailov
 * Data module exception IDs.
 */
public final class DataExceptionIds {
    /**
     * Invalid (null or blank) schema name.
     */
    public static final ExceptionId EX_DATA_STORAGE_INVALID_SCHEMA_NAME
            = new ExceptionId("EX_DATA_STORAGE_INVALID_SCHEMA_NAME", "app.data.storage.invalid.schema.name");
    /**
     * Interval type does not support unlock.
     */
    public static final ExceptionId EX_DATA_STORAGE_NOT_CONFIGURED
            = new ExceptionId("EX_DATA_STORAGE_NOT_CONFIGURED", "app.data.storage.not.configured");
    /**
     * Cluster metadata in invalid state.
     */
    public static final ExceptionId EX_DATA_STORAGE_INVALID_STATE
            = new ExceptionId("EX_DATA_STORAGE_INVALID_STATE", "app.data.storage.invalid.state");
    /**
     * Failed to start storage cluster.
     */
    public static final ExceptionId EX_DATA_STORAGE_START_FAILED
            = new ExceptionId("EX_DATA_STORAGE_START_FAILED", "app.data.start.storage.failed");
    /**
     * Failed to initialize storage cluster.
     */
    public static final ExceptionId EX_DATA_STORAGE_INIT_FAILED
            = new ExceptionId("EX_DATA_STORAGE_INIT_FAILED", "app.data.storage.init.failed");
    /**
     * Node line supplied in wrong format.
     */
    public static final ExceptionId EX_DATA_STORAGE_INIT_NODE_FORMAT
            = new ExceptionId("EX_DATA_STORAGE_INIT_NODE_FORMAT", "app.data.storage.node.format");
    /**
     * Supplied Node line contains no credentials.
     */
    public static final ExceptionId EX_DATA_STORAGE_INIT_NODE_NO_CREDENTIALS
            = new ExceptionId("EX_DATA_STORAGE_INIT_NODE_NO_CREDENTIALS", "app.data.storage.node.no.credentials");
    /**
     * Zero nodes initialized by the system.
     */
    public static final ExceptionId EX_DATA_STORAGE_ZERO_NODES_INITIALIZED
            = new ExceptionId("EX_DATA_STORAGE_ZERO_NODES_INITIALIZED", "app.data.storage.zero.nodes.initialized");
    /**
     * Invalid node requested.
     */
    public static final ExceptionId EX_DATA_STORAGE_INVALID_NODE_REQUESTED
            = new ExceptionId("EX_DATA_STORAGE_INVALID_NODE_REQUESTED", "app.data.storage.invalid.node.requested");
    /**
     * Shard to node calculated invalid result.
     */
    public static final ExceptionId EX_DATA_STORAGE_INVALID_SHARD_REQUESTED
            = new ExceptionId("EX_DATA_STORAGE_INVALID_SHARD_REQUESTED", "app.data.storage.invalid.shard.requested");
    /**
     * Shard numbers do not match.
     */
    public static final ExceptionId EX_DATA_STORAGE_SHARD_NUMBERS_DO_NOT_MATCH
            = new ExceptionId("EX_DATA_STORAGE_SHARD_NUMBERS_DO_NOT_MATCH", "app.data.storage.shard.numbers.do.not.match");
    /**
     * Invalid record identity.
     */
    public static final ExceptionId EX_DATA_RECORD_INVALID_KEYS
            = new ExceptionId("EX_DATA_RECORD_INVALID_KEYS", "app.data.records.invalid.record.keys");
    /**
     * Invalid relto identity.
     */
    public static final ExceptionId EX_DATA_RELATION_INVALID_KEYS
            = new ExceptionId("EX_DATA_RELATION_INVALID_KEYS", "app.data.rels.invalid.relation.keys");

    public static final ExceptionId EX_SYSTEM_JAXB_CANNOT_SET_FIELD_PERMISSION =
            new ExceptionId("EX_SYSTEM_JAXB_CANNOT_SET_FIELD_PERMISSION", "app.data.jaxbCannotSetFieldPermission");
    /**
     * Failed to migrate storage cluster metadata schema.
     */
    public static final ExceptionId EX_DATA_STORAGE_MIGRATE_META_FAILED =
            new ExceptionId("EX_DATA_STORAGE_MIGRATE_META_FAILED", "app.data.migrate.storage.meta.failed");
    /**
     * Migrate data schema failed.
     */
    public static final ExceptionId EX_DATA_STORAGE_MIGRATE_DATA_FAILED =
            new ExceptionId("EX_DATA_STORAGE_MIGRATE_DATA_FAILED", "app.data.migrate.schema.failed");
    /**
     * JAXB context init failure.
     */
    public static final ExceptionId EX_DATA_JAXB_CONTEXT_INIT_FAILURE =
            new ExceptionId("EX_DATA_JAXB_CONTEXT_INIT_FAILURE", "app.data.jaxbContextInitFailure");
    /**
     * Cannot unmarshal Original record.
     */
    public static final ExceptionId EX_DATA_CANNOT_UNMARSHAL_ORIGIN =
            new ExceptionId("EX_DATA_CANNOT_UNMARSHAL_ORIGIN", "app.data.cannotUnmarshallOrigin");
    /**
     * Cannot unmarshal Relation.
     */
    public static final ExceptionId EX_DATA_CANNOT_UNMARSHAL_RELATION =
            new ExceptionId("EX_DATA_CANNOT_UNMARSHAL_RELATION", "app.data.cannotUnmarshallRelation");
    /**
     * Cannot marshal Golden record.
     */
    public static final ExceptionId EX_DATA_CANNOT_MARSHAL_ETALON =
            new ExceptionId("EX_DATA_CANNOT_MARSHAL_ETALON", "app.data.cannotMarshallGolden");
    /**
     * Cannot marshal Original record.
     */
    public static final ExceptionId EX_DATA_CANNOT_MARSHAL_ORIGIN =
            new ExceptionId("EX_DATA_CANNOT_MARSHAL_ORIGIN", "app.data.cannotMarshallOrigin");
    /**
     * Cannot marshal Relation.
     */
    public static final ExceptionId EX_DATA_CANNOT_MARSHAL_RELATION =
            new ExceptionId("EX_DATA_CANNOT_MARSHAL_RELATION", "app.data.cannotMarshallRelation");

    public static final ExceptionId EX_DATA_INVALID_LOB_OBJECT =
            new ExceptionId("EX_DATA_INVALID_LOB_OBJECT", "app.data.invalidLobObject");

    public static final ExceptionId EX_DATA_CANNOT_PARSE_DATE =
            new ExceptionId("EX_DATA_CANNOT_PARSE_DATE", "app.data.cannotParseDate");

    public static final ExceptionId EX_DATA_UNSUPPORTED_DATA_TYPE =
            new ExceptionId("EX_DATA_UNSUPPORTED_DATA_TYPE", "app.data.unsupportedDataType");

    /**
     * Etalon insert failed.
     */
    public static final ExceptionId EX_DATA_ETALON_INSERT_FAILED =
            new ExceptionId("EX_DATA_ETALON_INSERT_FAILED", "app.data.goldenInsertFailed");
    /**
     * Etalon approval state change failed.
     */
    public static final ExceptionId EX_DATA_ETALON_APPROVAL_STATE_UPDATE_FAILED =
            new ExceptionId("EX_DATA_ETALON_APPROVAL_STATE_UPDATE_FAILED", "app.data.etalon.approval.state.change.failed");
    /**
     * Golden insert failed.
     */
    public static final ExceptionId EX_DATA_ETALON_UPDATE_FAILED =
            new ExceptionId("EX_DATA_ETALON_UPDATE_FAILED", "app.data.goldenUpdateFailed");
    /**
     * Batch insert to vistory failed.
     */
    public static final ExceptionId EX_DATA_INSERT_VISTORY_BATCH_FAILED =
            new ExceptionId("EX_DATA_INSERT_VISTORY_BATCH_FAILED", "app.data.insertVistoryBatchFailed");
    /**
     * Origin record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_BATCH_UPSERT_ORIGIN_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_BATCH_UPSERT_ORIGIN_FAILED", "app.data.originFailed");
    /**
     * Version record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_VERSION_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_VERSION_FAILED", "app.data.versionFailed");
    /**
     * Ensure keys failed.
     */
    public static final ExceptionId EX_DATA_IDENTIFY_RECORD_FAILED =
            new ExceptionId("EX_DATA_IDENTIFY_RECORD_FAILED", "app.data.identify.record.failed");
    /**
     * Mass key resolution failed.
     */
    public static final ExceptionId EX_DATA_TIMELINE_MASS_KEYS_NO_IDENTITY =
            new ExceptionId("EX_DATA_TIMELINE_MASS_KEYS_NO_IDENTITY", "app.data.timeline.noIdentity");
    /**
     * External ID can not be joined. Etalon ID not found.
     */
    public static final ExceptionId EX_DATA_JOIN_ETALON_ID_NOT_FOUND =
            new ExceptionId("EX_DATA_JOIN_ETALON_ID_NOT_FOUND", "app.data.join.etalon.id.not.found");
    /**
     * External ID can not be joined. Invalid input.
     */
    public static final ExceptionId EX_DATA_JOIN_INVALID_INPUT =
            new ExceptionId("EX_DATA_JOIN_INVALID_INPUT", "app.data.join.invalid.input");
    /**
     * External ID can not be joined. Target register and the supplied one do not match.
     */
    public static final ExceptionId EX_DATA_JOIN_TARGET_REGISTER_DONT_MATCH =
            new ExceptionId("EX_DATA_JOIN_TARGET_REGISTER_DONT_MATCH", "app.data.join.target.register.dont.match");
    /**
     * External ID can not be joined. The key is already defined for the target.
     */
    public static final ExceptionId EX_DATA_JOIN_KEY_IS_ALREADY_DEFINED_IN_TARGET =
            new ExceptionId("EX_DATA_JOIN_KEY_IS_ALREADY_DEFINED_IN_TARGET", "app.data.join.key.already.in");
    /**
     * External ID can not be joined. The key is already used by another record.
     */
    public static final ExceptionId EX_DATA_JOIN_KEY_IS_ALREADY_USED_BY_ANOTHER =
            new ExceptionId("EX_DATA_JOIN_KEY_IS_ALREADY_USED_BY_ANOTHER", "app.data.join.key.already.used");
    /**
     * Meta model type wrapper for id not found for BVT calculation.
     */
    public static final ExceptionId EX_DATA_NO_ENTITY_ELEMENT_FOR_BVT_CALCULATION =
            new ExceptionId("EX_DATA_NO_ENTITY_ELEMENT_FOR_BVT_CALCULATION", "app.data.noTypeWrapperForBVTCalculation");
    /**
     * Meta model type wrapper for id is not BVT capable.
     */
    public static final ExceptionId EX_DATA_ENTITY_ELEMENT_NOT_BVT_CAPABLE =
            new ExceptionId("EX_DATA_ENTITY_ELEMENT_NOT_BVT_CAPABLE", "app.data.noTypeWrapperForBVTCalculation");
    /**
     * Unable to generate externalId, using autogeneration strategy for entity {}.
     * Generated key length exceeds the limit of 512 characters.
     */
    public static final ExceptionId EX_DATA_UPSERT_ID_GENERATION_STRATEGY_KEY_LENGTH =
            new ExceptionId("EX_DATA_UPSERT_ID_GENERATION_STRATEGY_KEY_LENGTH", "app.data.upsert.id.generation.strategy.key.length");
    /**
     * Unable to generate externalId or code attribute, using autogeneration strategy for entity {}.
     * Either no data was given or content for configured fields is missing or incomplete. Processing {}.
     */
    public static final ExceptionId EX_DATA_UPSERT_UNABLE_TO_APPLY_ID_GENERATION_STRATEGY =
            new ExceptionId("EX_DATA_UPSERT_UNABLE_TO_APPLY_ID_GENERATION_STRATEGY", "app.data.upsert.unable.to.apply.id.generation.strategy");
    /**
     * Invalid upsert request context. Neither etalon record nor origin record has been supplied. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_NO_INPUT =
            new ExceptionId("EX_DATA_UPSERT_NO_INPUT", "app.data.upsertNoInput");
    /**
     * Record can not be identified by supplied keys. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_INVALID_KEYS =
            new ExceptionId("EX_DATA_UPSERT_INVALID_KEYS", "app.data.upsertInvalidKeys");
    /**
     * Upsert failed, origin is in inactive state.
     */
    public static final ExceptionId EX_DATA_UPSERT_ORIGIN_INACTIVE =
            new ExceptionId("EX_DATA_UPSERT_ORIGIN_INACTIVE", "app.data.upsertOriginInactive");
    /**
     * Upsert failed, etalon is in inactive state.
     */
    public static final ExceptionId EX_DATA_UPSERT_ETALON_INACTIVE =
            new ExceptionId("EX_DATA_UPSERT_ETALON_INACTIVE", "app.data.upsertEtalonInactive");
    /**
     * Invalid upsert request context. Neither external id nor origin record key has been supplied. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_INVALID_ORIGIN_INPUT =
            new ExceptionId("EX_DATA_UPSERT_INVALID_ORIGIN_INPUT", "app.data.upsertInvalidOriginInput");
    /**
     * Source system must be defined. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_NO_SOURCE_SYSTEM =
            new ExceptionId("EX_DATA_UPSERT_NO_SOURCE_SYSTEM", "app.data.upsertNoSourceSystem");
    /**
     * Time line not exist
     */
    public static final ExceptionId EX_DATA_RELATION_CONTEXT_NO_IDENTITY =
            new ExceptionId("EX_DATA_RELATION_CONTEXT_NO_IDENTITY", "app.data.timeline.noIdentity");
    /**
     * Invalid upsert request context. No entity name was supplied. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_NO_ID =
            new ExceptionId("EX_DATA_UPSERT_NO_ID", "app.data.upsertNoEntityName");
    /**
     * Invalid upsert request context. Entity was not found by name. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_ENTITY_NOT_FOUND_BY_NAME =
            new ExceptionId("EX_DATA_UPSERT_ENTITY_NOT_FOUND_BY_NAME", "app.data.upsertEntityNotFoundByName");
    /**
     * Required attributes is not presented.
     */
    public static final ExceptionId EX_DATA_UPSERT_REQUIRED_ATTRS_NOT_PRESENT =
            new ExceptionId("EX_DATA_UPSERT_REQUIRED_ATTRS_NOT_PRESENT", "app.data.upsert.required.attrs.notPresented");
    /**
     * Attribute supplied for upsert is missing in the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_INCORRECT_QUANTITY_OF_COMPLEX_ATTRIBUTES_IN_RANGE =
            new ExceptionId("EX_DATA_UPSERT_INCORRECT_QUANTITY_OF_COMPLEX_ATTRIBUTES_IN_RANGE", "app.data.upsert.incorrect.quantity.complex.attrs.range");
    /**
     * Attribute supplied for upsert is missing in the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_MISSING_ATTRIBUTE =
            new ExceptionId("EX_DATA_UPSERT_MISSING_ATTRIBUTE", "app.data.upsertMissingAttribute");
    /**
     * Attribute supplied for upsert is of the wrong type compared to the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_ATTRIBUTE_TYPE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_ATTRIBUTE_TYPE", "app.data.upsertWrongAttributeType");
    /**
     * Wrong code attribute link value. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_SIMPLE_CODE_ATTRIBUTE_REFERENCE_VALUE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_SIMPLE_CODE_ATTRIBUTE_REFERENCE_VALUE", "app.data.upsertWrongSpecialAttributeType");
    /**
     * Attribute supplied for upsert is of the wrong type compared to the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_SPECIAL_ATTRIBUTE_TYPE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_SPECIAL_ATTRIBUTE_TYPE", "app.data.upsertWrongSpecialAttributeType");
    /**
     * Enum attribute has a value which not present in system
     */
    public static final ExceptionId EX_DATA_UPSERT_ENUM_ATTRIBUTE_INCORRECT =
            new ExceptionId("EX_DATA_UPSERT_ENUM_ATTRIBUTE_INCORRECT", "app.data.upsert.attribute.enum.value.incorrect");
    /**
     * Value in measured attribute is not present
     */
    public static final ExceptionId EX_DATA_ATTRIBUTE_MEASURED_VALUE_NOT_PRESENT =
            new ExceptionId("EX_DATA_ATTRIBUTE_MEASURED_VALUE_NOT_PRESENT", "app.data.attribute.measured.value.notPresent");
    /**
     * Unit in measured attribute is not present
     */
    public static final ExceptionId EX_DATA_ATTRIBUTE_MEASURED_UNIT_NOT_PRESENT =
            new ExceptionId("EX_DATA_ATTRIBUTE_MEASURED_UNIT_NOT_PRESENT", "app.data.attribute.measured.unit.notPresent");
    /**
     * Attribute supplied for upsert is of the wrong value type compared to the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_CODE_ATTRIBUTE_VALUE_TYPE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_CODE_ATTRIBUTE_VALUE_TYPE", "app.data.upsertWrongCodeAttributeValueType");
    /**
     * Attribute supplied for upsert is of the wrong value type compared to the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_SIMPLE_ATTRIBUTE_VALUE_TYPE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_SIMPLE_ATTRIBUTE_VALUE_TYPE", "app.data.upsertWrongSimpleAttributeValueType");
    /**
     * Wrong code attribute link value. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_ARRAY_CODE_ATTRIBUTE_REFERENCE_VALUE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_ARRAY_CODE_ATTRIBUTE_REFERENCE_VALUE", "app.data.upsertWrongArrayCodeAttributeReferenceType");
    /**
     * Attribute supplied for upsert is of the wrong value type compared to the model. Upsert rejected.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_ARRAY_ATTRIBUTE_VALUE_TYPE =
            new ExceptionId("EX_DATA_UPSERT_WRONG_ARRAY_ATTRIBUTE_VALUE_TYPE", "app.data.upsertWrongArrayAttributeValueType");
    /**
     * Large object attribute has incorrect link to attach file
     */
    public static final ExceptionId EX_DATA_UPSERT_LARGE_OBJECT_VALUE_UNAVAILABLE =
            new ExceptionId("EX_DATA_UPSERT_LARGE_OBJECT_VALUE_UNAVAILABLE", "app.data.upsert.unavailable.large.object.value");
    /**
     * The user has no or unsufficient insert rights.
     */
    public static final ExceptionId EX_DATA_UPSERT_INSERT_NO_RIGHTS =
            new ExceptionId("EX_DATA_UPSERT_INSERT_NO_RIGHTS", "app.data.upsert.noRightsForInsert");
    /**
     * The user has no or unsufficient update rights.
     */
    public static final ExceptionId EX_DATA_UPSERT_UPDATE_NO_RIGHTS =
            new ExceptionId("EX_DATA_UPSERT_UPDATE_NO_RIGHTS", "app.data.upsert.noRightsForUpdate");
    /**
     * Soap user don't able to upsert record if record in pending state.
     */
    public static final ExceptionId EX_DATA_UPSERT_NOT_ACCEPTED_HAS_PENDING_RECORD =
            new ExceptionId("EX_DATA_UPSERT_NOT_ACCEPTED_HAS_PENDING_RECORD", "app.data.upsert.hasPendingVersions");
    /**
     * Incorrect validity range input.
     */
    public static final ExceptionId EX_DATA_VALIDITY_PERIOD_INCORRECT =
            new ExceptionId("EX_DATA_VALIDITY_PERIOD_INCORRECT", "app.data.validity.period.incorrect");
    /**
     * Alias code attribute is invalid
     */
    public static final ExceptionId EX_DATA_UPSERT_INVALID_ALIAS_CODE_ATTRIBUTE =
            new ExceptionId("EX_DATA_UPSERT_INVALID_ALIAS_CODE_ATTRIBUTE", "app.data.upsert.invalidAliasCodeAttribute");
    /**
     * Attribute and attribute definition have link to unavailable measurement value
     */
    public static final ExceptionId EX_DATA_UPSERT_MEASUREMENT_VALUE_UNAVAILABLE =
            new ExceptionId("EX_DATA_UPSERT_MEASUREMENT_VALUE_UNAVAILABLE", "app.data.upsert.unavailable.measurement.value");
    /**
     * Attribute and attribute definition have link to unavailable measurement value
     */
    public static final ExceptionId EX_MEASUREMENT_VALUE_DOESNT_EXIST =
            new ExceptionId("EX_MEASUREMENT_VALUE_DOESNT_EXIST", "app.measurement.value.doesnt.exist");
    /**
     * Attribute and attribute definition have different measurement values.
     */
    public static final ExceptionId EX_DATA_UPSERT_WRONG_MEASUREMENT_VALUES =
            new ExceptionId("EX_DATA_UPSERT_WRONG_MEASUREMENT_VALUES", "app.data.upsert.wrong.measurement.values");
    /**
     * Attribute has link to unavailable measurement unit
     */
    public static final ExceptionId EX_DATA_UPSERT_MEASUREMENT_UNIT_UNAVAILABLE =
            new ExceptionId("EX_DATA_UPSERT_MEASUREMENT_UNIT_UNAVAILABLE", "app.data.upsert.unavailable.measurement.unit");
    /**
     * Invalid get request context.
     */
    public static final ExceptionId EX_DATA_GET_INVALID_INPUT =
            new ExceptionId("EX_DATA_GET_INVALID_INPUT", "app.data.invalidGetInput");
    /**
     * Record not found by supplied keys.
     */
    public static final ExceptionId EX_DATA_GET_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_GET_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.notFoundByKeys");
    /**
     * Record not found by supplied keys.
     */
    public static final ExceptionId EX_DATA_PREVIEW_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_PREVIEW_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.notFoundByKeys");
    /**
     * Record not found by supplied keys.
     */
    public static final ExceptionId EX_DATA_PREVIEW_INVALID_INPUT =
            new ExceptionId("EX_DATA_PREVIEW_INVALID_INPUT", "app.data.preview.invalid.input");
    /**
     * Etalon found, but register names do not match.
     */
    public static final ExceptionId EX_ENTITY_NAME_AND_ETALON_ID_MISMATCH =
            new ExceptionId("EX_ENTITY_NAME_AND_ETALON_ID_MISMATCH", "entity_name_and_etalon_id_mismatch");
    /**
     * Record can not be read due to unsufficient rights.
     */
    public static final ExceptionId EX_DATA_READ_NO_RIGHTS =
            new ExceptionId("EX_DATA_GET_NO_RIGHTS", "app.data.get.noReadRights");
    /**
     * Invalid delete request context.
     */
    public static final ExceptionId EX_DATA_INVALID_DELETE_INPUT =
            new ExceptionId("EX_DATA_INVALID_DELETE_INPUT", "app.data.invalidDeleteInput");
    /**
     * No action was given to delete.
     */
    public static final ExceptionId EX_DATA_DELETE_ACTION_NOT_DEFINED =
            new ExceptionId("EX_DATA_DELETE_ACTION_NOT_DEFINED", "app.data.delete.action.not.defined");
    /**
     * More than one action was given to delete.
     */
    public static final ExceptionId EX_DATA_DELETE_MORE_THAN_ONE_ACTION_DEFINED =
            new ExceptionId("EX_DATA_DELETE_MORE_THAN_ONE_ACTION_DEFINED", "app.data.delete.more.than.one.action.defined");
    /**
     * More than one action was given to delete.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_MORE_THAN_ONE_ACTION_DEFINED =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_MORE_THAN_ONE_ACTION_DEFINED", "app.data.relations.delete.more.than.one.action.defined");
    /**
     * No action was given to relation delete.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_ACTION_NOT_DEFINED =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_ACTION_NOT_DEFINED", "app.data.relations.delete.action.not.defined");
    /**
     * Invalid input combination wipe cannot be drafted.
     */
    public static final ExceptionId EX_DATA_DELETE_WIPE_AND_DRAFT_COMBINATION =
            new ExceptionId("EX_DATA_DELETE_WIPE_AND_DRAFT_COMBINATION", "app.data.delete.wipe.and.draft.combination");
    /**
     * Invalid input combination wipe cannot be drafted.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_WIPE_AND_DRAFT_COMBINATION =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_WIPE_AND_DRAFT_COMBINATION", "app.data.relations.delete.wipe.and.draft.combination");
    /**
     * Record can not be deleted due to unsufficient rights.
     */
    public static final ExceptionId EX_DATA_DELETE_NO_RIGHTS =
            new ExceptionId("EX_DATA_DELETE_NO_RIGHTS", "app.data.delete.noReadRights");
    /**
     * An etalon record has incoming relations and cannot be deleted.
     */
    public static final ExceptionId EX_DATA_ETALON_HAS_INCOMING_RELATIONS =
            new ExceptionId("EX_DATA_ETALON_HAS_INCOMING_RELATIONS", "app.data.consistency.etalon.has.incoming.relations");
    /**
     * An etalon record has incoming links.
     */
    public static final ExceptionId EX_DATA_ETALON_HAS_INCOMING_LINKS =
            new ExceptionId("EX_DATA_ETALON_HAS_INCOMING_LINKS", "app.data.consistency.etalon.has.incoming.links");

    public static final ExceptionId EX_DATA_AUDIT_UNKNOW_PIPELINE_EXECUTION_CONTEXT =
            new ExceptionId("EX_DATA_AUDIT_UNKNOW_PIPELINE_EXECUTION_CONTEXT", "app.data.audit.unknow.pipeline.execution.context");

    public static final ExceptionId EX_DATA_PIPELINE_LOADING_ERROR =
            new ExceptionId("EX_DATA_PIPELINE_LOADING_ERROR", "app.data.pipeline.loading.error");

    /**
     * Relation not found by name.
     */
    public static final ExceptionId EX_DATA_RELATIONS_GET_RELATION_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_GET_RELATION_NOT_FOUND", "app.data.get.relationNotFoundByName");
    /**
     * Relation not found by name.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_RELATION_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_RELATION_NOT_FOUND", "app.data.relationNotFoundByName");
    /**
     * No read rights.
     */
    public static final ExceptionId EX_DATA_RELATIONS_GET_NO_RIGHTS =
            new ExceptionId("EX_DATA_RELATIONS_GET_NO_RIGHTS", "app.data.relations.noReadRights");
    /**
     * Relation not found by supplied keys.
     */
    public static final ExceptionId EX_DATA_RELATIONS_GET_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_RELATIONS_GET_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.relations.not.found.by.keys");
    /**
     * No insert rights.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_NO_INSERT_RIGHTS =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_NO_INSERT_RIGHTS", "app.data.relations.noInsertRights");
    /**
     * No update rights.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_NO_UPDATE_RIGHTS =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_NO_UPDATE_RIGHTS", "app.data.relations.noUpdateRights");
    /**
     * Invalid input.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_INVALID_INPUT =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_INVALID_INPUT", "app.data.relations.upsert.invalidInput");
    /**
     * From key not found.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_FROM_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_FROM_NOT_FOUND", "app.data.fromKeyNotFound");
    /**
     * To key not found.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_TO_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_TO_NOT_FOUND", "app.data.toKeyNotFound");
    /**
     * From or to side is in inactive state.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_SIDES_INACTIVE =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_SIDES_INACTIVE", "app.data.sidesInactive");
    /**
     * Containment record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_CONTAINS_KEYS_INVALID =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_CONTAINS_KEYS_INVALID", "app.data.containsKeysInvalid");
    /**
     * Containment record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_UPSERT_CONTAINS_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_UPSERT_CONTAINS_FAILED", "app.data.containsUpsertFailed");
    /**
     * Containment record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_CONTAINS_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_CONTAINS_FAILED", "app.data.relations.delete.contains.failed");
    /**
     * Containment record upsert failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_RESTORE_CONTAINS_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_RESTORE_CONTAINS_FAILED", "app.data.relations.restore.contains.failed");
    /**
     * Containment record get failed.
     */
    public static final ExceptionId EX_DATA_RELATIONS_GET_CONTAINS_FAILED =
            new ExceptionId("EX_DATA_RELATIONS_GET_CONTAINS_FAILED", "app.data.relations.get.contains.failed");
    /**
     * Relation not found by name.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_RELATION_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_RELATION_NOT_FOUND", "app.data.delete.relationNotFoundByName");
    /**
     * Relation not found.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_NOT_FOUND", "app.data.keysNotFound");
    /**
     * Relation keys are already in inactive state.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_ALREADY_INACTIVE =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_ALREADY_INACTIVE", "app.data.relations.delete.already.inactive");
    /**
     * Record keys are already in inactive state.
     */
    public static final ExceptionId EX_DATA_RECORDS_DELETE_ETALON_ALREADY_INACTIVE =
            new ExceptionId("EX_DATA_RECORDS_DELETE_ETALON_ALREADY_INACTIVE", "app.data.records.delete.etalon.already.inactive");
    /**
     * No delete rights.
     */
    public static final ExceptionId EX_DATA_RELATIONS_DELETE_NO_RIGHTS =
            new ExceptionId("EX_DATA_RELATIONS_DELETE_NO_RIGHTS", "app.data.relations.noDeleteRights");
    /**
     * No rel merge rights.
     */
    public static final ExceptionId EX_DATA_RELATIONS_MERGE_NO_RIGHTS =
            new ExceptionId("EX_DATA_RELATIONS_MERGE_NO_RIGHTS", "app.data.relations.merge.no.rights");
    /**
     * Input filters cannot be processed while partitioning.
     */
    public static final ExceptionId EX_DATA_PARTITION_FILTERS_FAILED =
            new ExceptionId("EX_DATA_PARTITION_FILTERS_FAILED", "app.data.partition.filters.failed");
    /**
     * Failed to partition records for shard [{}].
     */
    public static final ExceptionId EX_DATA_FAILED_TO_PARTITION_RECORDS
            = new ExceptionId("EX_DATA_FAILED_TO_PARTITION_RECORDS", "app.data.failed.to.partition.records");
    /**
     * Relation not found by supplied keys.
     */
    public static final ExceptionId EX_DATA_RELATIONS_MERGE_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_RELATIONS_MERGE_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.relations.not.found.by.keys");
    /**
     * Required side (FROM|TO) was not supplied.
     */
    public static final ExceptionId EX_DATA_RELATIONS_MERGE_REQUIRED_SIDE_NOT_SUPPLIED =
            new ExceptionId("EX_DATA_RELATIONS_MERGE_REQUIRED_SIDE_NOT_SUPPLIED", "app.data.relations.merge.required.side.not.supplied");
    /**
     * Winner keys not found.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_RECORD_MERGE_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.record.merge.not.found.by.supplied.keys");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_VALIDATE_INCORRECT_STATE =
            new ExceptionId("EX_DATA_RECORD_MERGE_VALIDATE_INCORRECT_STATE", "app.data.record.merge.validate.incorrect.state");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_CANNOT_EXECUTE =
            new ExceptionId("EX_DATA_RECORD_MERGE_CANNOT_EXECUTE", "app.data.record.merge.cannot.execute");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_NO_RIGHTS =
            new ExceptionId("EX_DATA_RECORD_MERGE_NO_RIGHTS", "app.data.record.merge.no.rights");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_NULLABLE_TIMELINE =
            new ExceptionId("EX_DATA_RECORD_MERGE_NULLABLE_TIMELINE", "app.data.record.merge.nullable.timeline");
    /**
     * Master key supplied twice - as master and duplicate.
     */
    public static final ExceptionId EX_DATA_RECORD_MERGE_MASTER_ID_IN_DUPLICATES =
            new ExceptionId("EX_DATA_RECORD_MERGE_MASTER_ID_IN_DUPLICATES", "app.data.record.merge.master.id.in.duplicates");
    /**
     * Cannot restore relation.
     */
    public static final ExceptionId EX_DATA_RELATIONS_RESTORE_NOT_FOUND_BY_SUPPLIED_KEYS =
            new ExceptionId("EX_DATA_RELATIONS_RESTORE_NOT_FOUND_BY_SUPPLIED_KEYS", "app.data.relations.restore.not.found.by.keys");
    /**
     * Cannot restore relation.
     */
    public static final ExceptionId EX_DATA_RELATIONS_RESTORE_ETALON_ACTIVE =
            new ExceptionId("EX_DATA_RELATIONS_RESTORE_ETALON_ACTIVE", "app.data.relations.restore.etalon.active");
    /**
     * Cannot restore relation period.
     */
    public static final ExceptionId EX_DATA_RELATIONS_RESTORE_PERIOD_INACTIVE =
            new ExceptionId("EX_DATA_RELATIONS_RESTORE_PERIOD_INACTIVE", "app.data.relations.restore.period.inactive");
    /**
     * Upsert failed, etalon is in inactive state.
     */
    public static final ExceptionId EX_DATA_RELATIONS_RESTORE_EMPTY_PERIOD =
            new ExceptionId("EX_DATA_RELATIONS_RESTORE_EMPTY_PERIOD", "app.data.relations.restore.empty.period");
    /**
     * Master key supplied twice - as master and duplicate.
     */
    public static final ExceptionId EX_DATA_RECORD_PREVIEW_MASTER_ID_IN_DUPLICATES =
            new ExceptionId("EX_DATA_RECORD_PREVIEW_MASTER_ID_IN_DUPLICATES", "app.data.record.merge.master.id.in.duplicates");
    /**
     * Record can not be identified by supplied keys. Restore rejected.
     */
    public static final ExceptionId EX_DATA_RESTORE_INVALID_KEYS =
            new ExceptionId("EX_DATA_RESTORE_INVALID_KEYS", "app.data.restore.invalid.keys");
    /**
     * Upsert failed, etalon is in inactive state.
     */
    public static final ExceptionId EX_DATA_RESTORE_ETALON_ACTIVE =
            new ExceptionId("EX_DATA_RESTORE_ETALON_ACTIVE", "app.data.restore.etalon.active");
    /**
     * Cannot restore period - record inactive.
     */
    public static final ExceptionId EX_DATA_RESTORE_PERIOD_INACTIVE =
            new ExceptionId("EX_DATA_RESTORE_PERIOD_INACTIVE", "app.data.restore.period.inactive");
    /**
     * Upsert failed, etalon is in inactive state.
     */
    public static final ExceptionId EX_DATA_RESTORE_PERIOD_NOT_COVERED =
            new ExceptionId("EX_DATA_RESTORE_PERIOD_NOT_COVERED", "app.data.restore.period.not.covered");
    /**
     * Upsert failed, etalon is in inactive state.
     */
    public static final ExceptionId EX_DATA_RESTORE_EMPTY_PERIOD =
            new ExceptionId("EX_DATA_RESTORE_EMPTY_PERIOD", "app.data.restore.empty.period");
    /**
     * No data exists for asOf date [{}].
     */
    public static final ExceptionId EX_DATA_RESTORE_EMPTY_RECORD =
            new ExceptionId("EX_DATA_RESTORE_EMPTY_RECORD", "app.data.restore.empty.record");
    /**
     * Attribute [{}] references missing lookup code values [{}].
     */
    public static final ExceptionId EX_DATA_RESTORE_MISSING_CODE_REFERENCED =
            new ExceptionId("EX_DATA_RESTORE_MISSING_CODE_REFERENCED", "app.data.restore.missing.code.referenced");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RECORD_RESTORE_NO_RIGHTS =
            new ExceptionId("EX_DATA_RECORD_RESTORE_NO_RIGHTS", "app.data.record.restore.no.rights");
    /**
     * Wrong winner state.
     */
    public static final ExceptionId EX_DATA_RELATION_RESTORE_NO_RIGHTS =
            new ExceptionId("EX_DATA_RELATION_RESTORE_NO_RIGHTS", "app.data.relation.restore.no.rights");
    /**
     * No entity name supplied for new record.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_NO_ENTITY_NAME =
            new ExceptionId("EX_DATA_RECORD_DRAFT_NO_ENTITY_NAME", "app.data.record.draft.no.entity.name");
    /**
     * No entity name supplied for new record.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_INCOMPLETE_EXTERNAL_ID =
            new ExceptionId("EX_DATA_RECORD_DRAFT_INCOMPLETE_EXTERNAL_ID", "app.data.record.draft.incomplete.external.id");
    /**
     * No entity name supplied for new record.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_SOURCE_SYSTEM_UNKNOWN =
            new ExceptionId("EX_DATA_RECORD_DRAFT_SOURCE_SYSTEM_UNKNOWN", "app.data.record.draft.source.system.unknown");
    /**
     * Record not found by subject ID.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_UNKNOWN_SUBJECT_ID =
            new ExceptionId("EX_DATA_RECORD_DRAFT_UNKNOWN_SUBJECT_ID", "app.data.record.draft.unknown.subject.id");
    /**
     * Unsupported operation type.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_UNSUPPORTED_OPERATION =
            new ExceptionId("EX_DATA_RECORD_DRAFT_UNSUPPORTED_OPERATION", "app.data.record.draft.unsupported.operation");
    /**
     * Subjects don't match.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_SUBJECTS_DONT_MATCH =
            new ExceptionId("EX_DATA_RECORD_DRAFT_SUBJECTS_DONT_MATCH", "app.data.record.draft.subjects.dont.match");
    /**
     * Subjects don't match.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_EXT_IDS_DONT_MATCH =
            new ExceptionId("EX_DATA_RECORD_DRAFT_EXT_IDS_DONT_MATCH", "app.data.record.draft.ext.ids.dont.match");
    /**
     * Subjects don't match.
     */
    public static final ExceptionId EX_DATA_RECORD_DRAFT_LSNS_DONT_MATCH =
            new ExceptionId("EX_DATA_RECORD_DRAFT_LSNS_DONT_MATCH", "app.data.record.draft.lsns.dont.match");
    /**
     * No entity name supplied for new relation.
     */
    public static final ExceptionId EX_DATA_RELATION_DRAFT_NO_ENTITY_NAME =
            new ExceptionId("EX_DATA_RELATION_DRAFT_NO_ENTITY_NAME", "app.data.relation.draft.no.entity.name");
    /**
     * Subjects don't match.
     */
    public static final ExceptionId EX_DATA_RELATION_DRAFT_SUBJECTS_DONT_MATCH =
            new ExceptionId("EX_DATA_RELATION_DRAFT_SUBJECTS_DONT_MATCH", "app.data.relation.draft.subjects.dont.match");
    /**
     * Subjects don't match.
     */
    public static final ExceptionId EX_DATA_RELATION_DRAFT_LSNS_DONT_MATCH =
            new ExceptionId("EX_DATA_RELATION_DRAFT_LSNS_DONT_MATCH", "app.data.relation.draft.lsns.dont.match");
    /**
     * Unsupported operation type.
     */
    public static final ExceptionId EX_DATA_RELATION_DRAFT_UNSUPPORTED_OPERATION =
            new ExceptionId("EX_DATA_RELATION_DRAFT_UNSUPPORTED_OPERATION", "app.data.relation.draft.unsupported.operation");
    /**
     * Cannot publish relation. Containment draft not found.
     */
    public static final ExceptionId EX_DATA_RELATION_DRAFT_CONTAINMENT_DRAFT_NOT_FOUND =
            new ExceptionId("EX_DATA_RELATION_DRAFT_CONTAINMENT_DRAFT_NOT_FOUND", "app.data.relation.draft.containment.draft.not.found");
    /**
     * Generally invalid input.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_INPUT_PARAMETERS_MISSING =
            new ExceptionId("EX_DATA_XLSX_IMPORT_INPUT_PARAMETERS_MISSING", "app.data.xlsx.import.input.parameters.missing");
    /**
     * Unable to parse xlsx file.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_PARSE_FILE_INVALID_CELL_FORMAT =
            new ExceptionId("EX_DATA_XLSX_IMPORT_PARSE_FILE_INVALID_CELL_FORMAT", "app.data.xlsx.import.parse.invalid.cell.format");
    /**
     * Unable to parse xlsx file.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_UNKNOWN_FILE_FORMAT
            = new ExceptionId("EX_DATA_XLSX_IMPORT_UNKNOWN_FILE_FORMAT", "app.data.xlsx.import.unknown.file.format");
    /**
     * Entity name is not known to the current metamodel.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_UNKNOWN_ENTITY
            = new ExceptionId("EX_DATA_XLSX_IMPORT_UNKNOWN_ENTITY", "app.data.xlsx.import.unknownEntityName");
    /**
     * Invalid file structure..
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_INVALID_FILE_STRUCTURE
            = new ExceptionId("EX_DATA_XLSX_IMPORT_INVALID_FILE_STRUCTURE", "app.data.xlsx.import.invalid.file.structure");
    /**
     * Unable to create XLS template file.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_UNABLE_TO_CREATE_TEMPLATE
            = new ExceptionId("EX_DATA_XLSX_IMPORT_UNABLE_TO_CREATE_TEMPLATE", "app.data.xlsx.import.unableToCreateTemplate");
    /**
     * Unable to save xlsx file.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_SAVE_FILE
            = new ExceptionId("EX_DATA_XLSX_IMPORT_SAVE_FILE", "app.data.xlsx.import.unableToSaveFile");
    /**
     * Unable to parse xlsx file.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_PARSE_FILE
            = new ExceptionId("EX_DATA_XLSX_IMPORT_PARSE_FILE", "app.data.xlsx.import.unableToParseFile");
    /**
     * Duplicated keys.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_DUPLICATED_KEYS
            = new ExceptionId("EX_DATA_XLSX_IMPORT_DUPLICATED_KEYS", "app.data.xlsx.import.duplicatedKeys");
    /**
     * Duplicate ids detected (etalon, or external).
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_DUPLICATED_IDS
            = new ExceptionId("EX_DATA_XLSX_IMPORT_DUPLICATED_IDS", "app.data.xlsx.import.duplicate.ids");
    /**
     * Non-unique values detected.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_NON_UNIQUE_VALUES
            = new ExceptionId("EX_DATA_XLSX_IMPORT_NON_UNIQUE_VALUES", "app.data.xlsx.import.non.unique.values");
    /**
     * Unable to save data into temporary table.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_TEMPORARY_TABLE
            = new ExceptionId("EX_DATA_XLSX_IMPORT_TEMPORARY_TABLE", "app.data.xlsx.import.unableToSaveData");
    /**
     * Top level sheet not found.
     */
    public static final ExceptionId EX_DATA_XLSX_IMPORT_TOP_LEVEL_SHEET_NOT_FOUND
            = new ExceptionId("EX_DATA_XLSX_IMPORT_TOP_LEVEL_SHEET_NOT_FOUND", "app.data.xlsx.import.toplevel.sheet.not.found");
    /**
     *
     * Constructor.
     */
    private DataExceptionIds() {
        super();
    }
}
