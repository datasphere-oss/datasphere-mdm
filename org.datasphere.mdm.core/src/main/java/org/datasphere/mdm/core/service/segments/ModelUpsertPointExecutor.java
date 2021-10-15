/*
 * Unidata Platform Community Edition
 * Copyright (c) 2013-2020, UNIDATA LLC, All rights reserved.
 * This file is part of the Unidata Platform Community Edition software.
 *
 * Unidata Platform Community Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Unidata Platform Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.datasphere.mdm.core.service.segments;

import java.util.Objects;

import org.datasphere.mdm.core.context.ModelChangeContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.datasphere.mdm.core.service.MetaModelService;
import org.datasphere.mdm.core.type.model.ModelImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Point;
import org.datasphere.mdm.system.type.pipeline.Start;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelUpsertPointExecutor.SEGMENT_ID)
public class ModelUpsertPointExecutor extends Point<ModelChangeContext> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_UPSERT_POINT]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.upsert.point.description";
    /**
     * MMS. Cheap and dirty.
     */
    @Autowired
    private MetaModelService metaModelService;
    /**
     * Constructor.
     */
    public ModelUpsertPointExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void point(ModelChangeContext ctx) {

        ModelImplementation<?> mi = metaModelService.implementation(ctx.getTypeId());
        if (Objects.nonNull(mi)) {
            mi.upsert(ctx);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelChangeContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
