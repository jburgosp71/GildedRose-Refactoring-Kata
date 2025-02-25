package com.gildedrose;

class GildedRose {
    public static final int HIGHEST_QUALITY = 50;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE)) {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS)) {
                        item.quality = decreaseQuality(item);
                    }
                }
            } else {
                if (isUnderHighestQualityValue(item)) {
                    item.quality = increaseQuality(item);

                    if (item.name.equals(BACKSTAGE)) {
                        if (item.sellIn < 11) {
                            if (isUnderHighestQualityValue(item)) {
                                item.quality = increaseQuality(item);
                            }
                        }

                        if (item.sellIn < 6) {
                            if (isUnderHighestQualityValue(item)) {
                                item.quality = increaseQuality(item);
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = decreaseSellIn(item);
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(SULFURAS)) {
                                item.quality = decreaseQuality(item);
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (isUnderHighestQualityValue(item)) {
                        item.quality = increaseQuality(item);
                    }
                }
            }
        }
    }

    private static int decreaseSellIn(Item item) {
        return item.sellIn - 1;
    }

    private static int increaseQuality(Item item) {
        return item.quality + 1;
    }

    private static int decreaseQuality(Item item) {
        return item.quality - 1;
    }

    private static boolean isUnderHighestQualityValue(Item item) {
        return item.quality < HIGHEST_QUALITY;
    }
}
