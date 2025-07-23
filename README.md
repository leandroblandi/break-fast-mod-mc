# 🪓 BreakFast - 3x3 Mining Mod (Server-side) for Forge 1.19.2+

**BreakFast** is a lightweight, server-side Minecraft Forge mod that allows players to mine in a 3x3 area with blazing speed — only when they want to.

Designed for survival servers and builders who want more efficient block breaking, **without requiring any client installation**.

---

## 🎮 Features

- 🔘 **Toggle mining mode** with simple commands:
    - `/bf enable` → Enables 3x3 mining
    - `/bf disable` → Disables it
- 🧠 **Smart config system**:
    - Enable mining only when using a pickaxe or shovel
    - Set the **maximum number of blocks** to break (not always 9)
- 🧼 **Server-side only** — no client-side installation required
- 🔐 **Permission support**: by default, only operators can toggle the mode

---

## ⚙️ Configuration

After first run, the mod generates a config file at:

```
/config/breakfastmod-common.toml
```

Example:

```toml
[general]
    onlyWithTools = true
    maxBlocks = 9
```

---

## 💻 Installation

1. Install [Minecraft Forge 1.19.2](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.19.2.html)
2. Drop the mod `.jar` into your server’s `mods/` folder
3. Start the server — config file will generate automatically

---

## 🔧 Requirements

- Minecraft **1.19.2**
- **Forge** loader
- **Server-side only** (clients do **not** need the mod)

---

## 📜 License

MIT © [Leandro Blandi](https://github.com/leandroblandi)

---

## 🙌 Contributing

PRs and ideas are welcome. Feel free to open issues if you want to improve or extend functionality (e.g. pickaxe blacklist, advanced radius options, etc).
