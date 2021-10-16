
package org.datasphere.mdm.core.service.impl.upath;

import java.util.Map;
import java.util.Objects;

import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.upath.UPathConstants;
import org.datasphere.mdm.core.type.upath.UPathElementType;
import org.datasphere.mdm.core.type.upath.UPathFilterElement;
import org.mvel2.MVEL;
import org.mvel2.compiler.CompiledExpression;

/**
 * @author Mikhail Mikhailov on Feb 26, 2021
 * MVEL Expression filter.
 */
public class ExpressionElementImpl extends AbstractElementImpl implements UPathFilterElement {
    /**
     * Compiled MVEL expression.
     */
    private final CompiledExpression expression;
    /**
     * Constructor.
     * @param element the source
     * @param expression compiled MVEL expression
     */
    public ExpressionElementImpl(String element, CompiledExpression expression) {
        super(element, UPathElementType.EXPRESSION);
        this.expression = expression;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(DataRecord record) {

        if (Objects.nonNull(record)) {
            return MVEL.executeExpression(expression, Map.of(UPathConstants.UPATH_RECORD_NAME, record), Boolean.class);
        }

        return false;
    }
}
