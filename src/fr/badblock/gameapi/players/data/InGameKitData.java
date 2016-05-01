package fr.badblock.gameapi.players.data;

import fr.badblock.gameapi.players.kits.PlayerKit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * InGameData pour gérer le kit choisit par le joueur
 * @author LeLanN
 */
@NoArgsConstructor
public class InGameKitData implements InGameData {
	@Getter@Setter private PlayerKit choosedKit;
}
