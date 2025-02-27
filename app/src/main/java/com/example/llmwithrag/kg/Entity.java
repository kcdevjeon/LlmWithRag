package com.example.llmwithrag.kg;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class Entity {
    private final Map<String, String> attributes;
    private final String id;
    private final String type;
    private final String name;

    public Entity(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
        attributes = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getContentId() {
        return String.valueOf(hash(getDescription().toString()));
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Map<String, String> getDescription() {
        Map<String, String> description = new HashMap<>(attributes);
        description.put("type", type);
        description.put("name", name);
        return description;
    }

    private long hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.wrap(hash);
            return buffer.getLong();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return -1;
    }

    @NonNull
    @Override
    public String toString() {
        Map<String, String> description = new HashMap<>(getDescription());
        description.put("id", id);
        return description.toString();
    }
}
