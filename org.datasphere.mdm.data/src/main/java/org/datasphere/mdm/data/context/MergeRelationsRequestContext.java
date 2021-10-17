package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.InputFragment;

/**
 * This input context is different, compared to other relations contexts:
 * - the record identity denotes the MASTER (or winner) key.
 * - it should contain either a combination of relation names or applyToAll flag and a list of duplicates, that will lose their relations for the winner/master
 *   or a map of filled from|to relation contexts, that will be owned by the master
 * Either one will be processed. Map with contexts has higher proiority.
 * @author Mikhail Mikhailov on May 2, 2020
 */
public class MergeRelationsRequestContext extends AbstractRelationsFromToRequestContext<MergeFromRelationRequestContext, MergeToRelationRequestContext>
        implements
            InputFragment<MergeRelationsRequestContext>,
            MergeDuplicatesContext<OriginRelation>,
            ReadWriteDataContext<OriginRelation> {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -2770004457875707763L;
    /**
     * This context fragment id.
     */
    public static final FragmentId<MergeRelationsRequestContext> FRAGMENT_ID
        = new FragmentId<>("MERGE_RELATIONS_REQUEST");
    /**
     * Duplicates list.
     */
    private final transient List<RecordIdentityContext> duplicates;
    /**
     * Selected 'FROM' relations names, to perform the operation on.
     */
    private final transient Set<String> fromRelationNames;
    /**
     * Selected 'TO' relations names, to perform the operation on.
     */
    private final transient Set<String> toRelationNames;
    /**
     * Constructor.
     * @param b the builder
     */
    private MergeRelationsRequestContext(MergeRelationsRequestContextBuilder b) {
        super(b);
        this.duplicates = b.duplicates;
        this.fromRelationNames = b.fromRelationNames;
        this.toRelationNames = b.toRelationNames;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentId<MergeRelationsRequestContext> fragmentId() {
        return FRAGMENT_ID;
    }
    /**
     * @return the duplicates
     */
    public List<RecordIdentityContext> getDuplicates() {
        return Objects.isNull(duplicates) ? Collections.emptyList() : duplicates;
    }
    /**
     * @return the fromRelationNames
     */
    public Set<String> getFromRelationNames() {
        return Objects.isNull(fromRelationNames) ? Collections.emptySet() : fromRelationNames;
    }
    /**
     * @return the toRelationNames
     */
    public Set<String> getToRelationNames() {
        return Objects.isNull(toRelationNames) ? Collections.emptySet() : toRelationNames;
    }
    /**
     * The builder.
     * @return builder
     */
    public static MergeRelationsRequestContextBuilder builder() {
        return new MergeRelationsRequestContextBuilder();
    }
    /**
     * Builder class.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public static class MergeRelationsRequestContextBuilder
        extends AbstractRelationsFromToRequestContextBuilder
            <MergeFromRelationRequestContext, MergeToRelationRequestContext, MergeRelationsRequestContextBuilder> {
        /**
         * The duplicate RECORDS.
         */
        private List<RecordIdentityContext> duplicates;
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         */
        private Set<String> fromRelationNames;
        /**
         * Selected 'TO' relations names, to perform the operation on.
         */
        private Set<String> toRelationNames;
        /**
         * Constructor.
         */
        private MergeRelationsRequestContextBuilder() {
            super();
        }
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public MergeRelationsRequestContextBuilder fromRelationNames(Collection<String> names) {
            if (CollectionUtils.isNotEmpty(names)) {
                fromRelationNames(names.toArray(String[]::new));
            }
            return self();
        }
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public MergeRelationsRequestContextBuilder fromRelationNames(String... names) {
            if (ArrayUtils.isNotEmpty(names)) {
                for (String n : names) {
                    if (Objects.isNull(fromRelationNames)) {
                        fromRelationNames = new HashSet<>(names.length);
                    }

                    if (StringUtils.isBlank(n)) {
                        continue;
                    }

                    fromRelationNames.add(n);
                }
            }
            return self();
        }
        /**
         * Selected 'TO' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public MergeRelationsRequestContextBuilder toRelationNames(Collection<String> names) {
            if (CollectionUtils.isNotEmpty(names)) {
                toRelationNames(names.toArray(String[]::new));
            }
            return self();
        }
        /**
         * Selected 'TO' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public MergeRelationsRequestContextBuilder toRelationNames(String... names) {
            if (ArrayUtils.isNotEmpty(names)) {
                for (String n : names) {
                    if (Objects.isNull(toRelationNames)) {
                        toRelationNames = new HashSet<>(names.length);
                    }

                    if (StringUtils.isBlank(n)) {
                        continue;
                    }

                    toRelationNames.add(n);
                }
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public MergeRelationsRequestContextBuilder duplicates(List<RecordIdentityContext> duplicates) {
            if (CollectionUtils.isNotEmpty(duplicates)) {
                duplicates(duplicates.toArray(RecordIdentityContext[]::new));
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public MergeRelationsRequestContextBuilder duplicates(RecordIdentityContext... duplicates) {
            if (ArrayUtils.isNotEmpty(duplicates)) {
                for (RecordIdentityContext ctx : duplicates) {
                    duplicate(ctx);
                }
            }
            return self();
        }
        /**
         * Sets the duplicate.
         * @param duplicate the duplicate to set
         * @return self
         */
        public MergeRelationsRequestContextBuilder duplicate(RecordIdentityContext duplicate) {
            if (Objects.nonNull(duplicate)) {
                if (Objects.isNull(this.duplicates)) {
                    this.duplicates = new ArrayList<>();
                }

                this.duplicates.add(duplicate);
            }
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public MergeRelationsRequestContext build() {
            return new MergeRelationsRequestContext(this);
        }
    }
}
