PGDMP  7    &                |            bank_of_pac    16.2 (Debian 16.2-1.pgdg120+2)    16.1     #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            &           1262    106501    bank_of_pac    DATABASE     v   CREATE DATABASE bank_of_pac WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE bank_of_pac;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            '           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    106514    mst_account_seq    SEQUENCE     y   CREATE SEQUENCE public.mst_account_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.mst_account_seq;
       public          postgres    false    4            �            1259    106516    mst_transaction    TABLE     �   CREATE TABLE public.mst_transaction (
    transaction_id bigint NOT NULL,
    amount double precision,
    date timestamp(6) without time zone,
    from_account bigint,
    to_account bigint,
    type character varying(255)
);
 #   DROP TABLE public.mst_transaction;
       public         heap    postgres    false    4            �            1259    106528    mst_transaction_seq    SEQUENCE     }   CREATE SEQUENCE public.mst_transaction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.mst_transaction_seq;
       public          postgres    false    4            �            1259    106521    mst_user    TABLE     �   CREATE TABLE public.mst_user (
    user_id bigint NOT NULL,
    balance double precision,
    user_email character varying(255),
    user_name character varying(255)
);
    DROP TABLE public.mst_user;
       public         heap    postgres    false    4            �            1259    106515    mst_user_seq    SEQUENCE     v   CREATE SEQUENCE public.mst_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.mst_user_seq;
       public          postgres    false    4                      0    106516    mst_transaction 
   TABLE DATA           g   COPY public.mst_transaction (transaction_id, amount, date, from_account, to_account, type) FROM stdin;
    public          postgres    false    217   .                 0    106521    mst_user 
   TABLE DATA           K   COPY public.mst_user (user_id, balance, user_email, user_name) FROM stdin;
    public          postgres    false    218   u       (           0    0    mst_account_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.mst_account_seq', 51, true);
          public          postgres    false    215            )           0    0    mst_transaction_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.mst_transaction_seq', 1, true);
          public          postgres    false    219            *           0    0    mst_user_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.mst_user_seq', 101, true);
          public          postgres    false    216            �           2606    106520 $   mst_transaction mst_transaction_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.mst_transaction
    ADD CONSTRAINT mst_transaction_pkey PRIMARY KEY (transaction_id);
 N   ALTER TABLE ONLY public.mst_transaction DROP CONSTRAINT mst_transaction_pkey;
       public            postgres    false    217            �           2606    106527    mst_user mst_user_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.mst_user
    ADD CONSTRAINT mst_user_pkey PRIMARY KEY (user_id);
 @   ALTER TABLE ONLY public.mst_user DROP CONSTRAINT mst_user_pkey;
       public            postgres    false    218               7   x�3�45 N##]s]#cC+Cs+c3=sCNS#NScΔԤ��=... ��	_         `   x�35�44 ά������Ң�T��������\N/���X��Ԉ��6#?�<39Ya~F�B8P��Ԙ��,17�8)?/�@�����=... 0'�     