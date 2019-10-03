package ir.ahfz.rentcar.io.network.model;

import android.graphics.Color;

import ir.ahfz.rentcar.R;

public enum CarBrand {
    BMW {
        @Override
        public int getColor() {
            return Color.parseColor("#5969FB");
        }

        @Override
        public int getDrawable() {
            return R.drawable.ic_bmw;
        }

        @Override
        public int getNameResId() {
            return R.string.bmw;
        }
    },
    IranKhodro {
        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public int getDrawable() {
            return 0;
        }

        @Override
        public int getNameResId() {
            return 0;
        }
    },
    Saipa {
        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public int getDrawable() {
            return 0;
        }

        @Override
        public int getNameResId() {
            return 0;
        }
    },
    Audi {
        @Override
        public int getColor() {
            return Color.parseColor("#555555");
        }

        @Override
        public int getDrawable() {
            return R.drawable.ic_audi2;
        }

        @Override
        public int getNameResId() {
            return R.string.audi;
        }
    },
    Toyota {
        @Override
        public int getColor() {
            return Color.parseColor("#FB5969");
        }

        @Override
        public int getDrawable() {
            return R.drawable.ic_toyota;
        }

        @Override
        public int getNameResId() {
            return R.string.toyota;
        }
    },
    Volvo {
        @Override
        public int getColor() {
            return Color.parseColor("#5969FB");
        }

        @Override
        public int getDrawable() {
            return R.drawable.ic_volvo_2;
        }

        @Override
        public int getNameResId() {
            return R.string.volvo;
        }
    },
    VW {
        @Override
        public int getColor() {
            return Color.parseColor("#5969FB");
        }

        @Override
        public int getDrawable() {
            return R.drawable.ic_volkswagen;
        }

        @Override
        public int getNameResId() {
            return R.string.volkswagon;
        }
    };


    public abstract int getColor();

    public abstract int getDrawable();

    public abstract int getNameResId();

}
