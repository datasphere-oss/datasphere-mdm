

package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov on Oct 21, 2020
 */
public interface PresentationBlockElement {
    /**
     * The block title.
     * @return title
     */
    String getTitle();
    /**
     * Number of columns.
     * @return columns
     */
    int getColumn();
    /**
     * Number of rows.
     * @return rows
     */
    int getRow();
}
