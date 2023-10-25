package org.example.commons.model;

import java.util.List;

public record Trainer(String id, String name, Level level, List<Pokemon> pokemons) {

}
