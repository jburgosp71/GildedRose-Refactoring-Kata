package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void Item_decrease_quality_double_when_sellIn_under_0() {
        Item[] items = new Item[] { new Item("Item", 1, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void Item_quality_never_under_0() {
        Item[] items = new Item[] { new Item("Item", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void Aged_Brie_item_increase_quality() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void Aged_Brie_item_increase_2_quality_when_sellin_under_0() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void Aged_Brie_item_not_increase_quality_when_quality_is_50() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void Sulfuras_not_decrease_sellIn_not_decrease_quality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void BackStage_increase_quality_when_sellin_greater_than_10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    void BackStage_increase_quality_2_when_sellin_greater_than_5_and_lower_than_11() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void BackStage_increase_quality_3_when_sellin_lower_than_5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void BackStage_quality_0_when_sellin_lower_than_0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

}
