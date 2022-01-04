package ca.ucalgary.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Used for formatting date/time with JSON files
 */
public class JsonDateSerializer extends JsonSerializer <LocalDateTime>{

	// Set DateTimeFormatter variable
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * serialize
     * @param date
     * @param generator
     * @param arg2
     * @throws IOException
     */
    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider arg2) throws IOException {
        final String dateString = date.format(this.formatter);
        generator.writeString(dateString);
    }
    
}