# Villager Biome Swapper

> This is a simple, self-contained plugin. For a more representative example of my work, take a look at [Dungeons](https://github.com/Eggses/Dungeons).

A Paper plugin that lets players change a villager's biome type through a clean GUI. Swapping is permission-gated per biome and restricted to level 1 villagers with no XP.

**Download:** [Spigot](https://www.spigotmc.org/resources/villager-biome-swapper.126357/) · **Discord:** [discord.gg/erDzWtEQXV](https://discord.gg/erDzWtEQXV)

> **Full documentation:** [eggsess-plugins.gitbook.io](https://eggsess-plugins.gitbook.io/eggsess-plugins/villager-biome-swapper/introduction-and-installation)

## Requirements

- Paper 1.21

## Features

- **Biome swap GUI** — Players right-click a villager with the swap item to open a clean selection menu.
- **Permission-based biome access** — Grant players access to specific biomes only, giving you full control.
- **Villager restrictions** — Only level 1 villagers with no XP can be swapped, keeping the mechanic balanced.
- **MiniMessage support** — All messages support MiniMessage formatting (or legacy colour codes).
- **Fully configurable** — All settings, messages, GUI items, and the swap item itself are customizable.

## Commands

All commands use `/vbs` (or `/villagerbiomeswapper`).

| Command | Description | Permission |
|---|---|---|
| `/vbs give [player]` | Give the swap item to yourself or a player | `villagerbiomeswapper.give` |
| `/vbs giveall` | Give the swap item to all online players | `villagerbiomeswapper.give` |
| `/vbs reload [config\|messages\|gui\|item]` | Reload config files | `villagerbiomeswapper.reload` |
| `/vbs help` | Show available commands | `villagerbiomeswapper.help` |

> ⚠️ The swap GUI requires no command. Players need `villagerbiomeswapper.convert.entity.villager` plus a biome permission (e.g. `villagerbiomeswapper.convert.biome.desert`). Use `villagerbiomeswapper.convert.biome.*` to grant all biomes.

## Configuration

| File | Description |
|---|---|
| `config.yml` | Core plugin settings |
| `messages.yml` | All player-facing messages. Supports MiniMessage and placeholders like `%player%`, `%target%` |
| `gui.yml` | Swap menu layout — item names, lore, and titles |
| `item.yml` | Defines the Villager Biome Swapper item — material, name, and lore |
