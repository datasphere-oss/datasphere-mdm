

package org.datasphere.mdm.core.dto.reports.csv;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.datasphere.mdm.core.dto.reports.Report;

/**
 * Stateful class responsible for creating csv report.
 */
public class CvsReport implements Report<String> {

    /**
     * New row
     */
    private static final String NEW_ROW = "\n";

    /**
     * Separator
     */
    private final char separator;

    /**
     * CharSet
     */
    @Nonnull
    private final Charset charSet;

    /**
     * Current state of csv
     */
    private final StringBuffer result = new StringBuffer();

    /**
     * Constructor
     *
     * @param separator - csv separator
     * @param charSet   - returned charSet
     */
    public CvsReport(char separator, @Nullable String charSet) {
        this.separator = separator;
        this.charSet = charSet == null ? Charset.forName(StandardCharsets.UTF_8.name()) : Charset.forName(charSet);
    }

    /**
     * Constructor where will be default charset.
     *
     * @param separator - csv separator
     */
    public CvsReport(char separator) {
        this.separator = separator;
        this.charSet = Charset.forName(StandardCharsets.UTF_8.name());
    }

    @Override
    public void newRow() {
        result.append(NEW_ROW);
    }

    @Override
    public void addElement(@Nonnull String element) {
        String finalElement = element.replace(separator, ' ').replace(NEW_ROW, "");
        result.append(finalElement);
        result.append(separator);
    }

    @Nonnull
    @Override
    public byte[] generate() {
        return result.toString().getBytes(charSet);
    }
}
