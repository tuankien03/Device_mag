--
-- PostgreSQL database dump
--

-- Dumped from database version 12.22 (Ubuntu 12.22-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 17.2 (Ubuntu 17.2-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: device_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.device_status AS ENUM (
    'AVAILABLE',
    'ASSIGNED',
    'RETURNING'
);


ALTER TYPE public.device_status OWNER TO postgres;

--
-- Name: user_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_role AS ENUM (
    'ADMIN',
    'USER'
);


ALTER TYPE public.user_role OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: assignment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assignment (
    assignment_id uuid DEFAULT public.gen_random_uuid() NOT NULL,
    user_id uuid NOT NULL,
    device_id uuid NOT NULL,
    assigned_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    returned_at timestamp without time zone
);


ALTER TABLE public.assignment OWNER TO postgres;

--
-- Name: device; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.device (
    device_id uuid DEFAULT public.gen_random_uuid() NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(255),
    status character varying(20) DEFAULT 'AVAILABLE'::public.device_status NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.device OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id uuid DEFAULT public.gen_random_uuid() NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(80) NOT NULL,
    role character varying(20) DEFAULT 'USER'::character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_device; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.user_device AS
 SELECT d.name AS device_name,
    d.description AS description_device,
    d.status AS status_device,
    u.username,
    u.role,
    u.user_id,
    d.device_id,
    a.assigned_at,
    a.returned_at
   FROM ((public.assignment a
     JOIN public."user" u ON ((a.user_id = u.user_id)))
     JOIN public.device d ON ((a.device_id = d.device_id)));


ALTER VIEW public.user_device OWNER TO postgres;

--
-- Name: user_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_session (
    session_id uuid DEFAULT public.gen_random_uuid() NOT NULL,
    user_id uuid NOT NULL,
    token character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expires_at timestamp without time zone NOT NULL,
    revoked boolean DEFAULT false
);


ALTER TABLE public.user_session OWNER TO postgres;

--
-- Name: user_session_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_session_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_session_seq OWNER TO postgres;

--
-- Data for Name: assignment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.assignment (assignment_id, user_id, device_id, assigned_at, returned_at) FROM stdin;
\.


--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device (device_id, name, description, status, created_at, updated_at) FROM stdin;
f780c3cd-823b-45e3-a15b-dbd46a5627a5	Router	Router Wi-Fi (hay còn gọi là bộ định tuyến không dây) là một thiết bị mạng dùng để phân phối kết nối Internet cho nhiều thiết bị khác nhau như điện thoại, máy tính, máy tính bảng, tivi thông minh,... thông qu	AVAILABLE	2025-02-05 15:17:32.051451	2025-02-05 15:17:32.051471
d5a6692f-ac2a-43a3-aaff-0c5c8a7fa18a	Router	Router Wi-Fi (hay còn gọi là bộ định tuyến không dây) là một thiết bị mạng dùng để phân phối kết nối Internet cho nhiều thiết bị khác nhau như điện thoại, máy tính, máy tính bảng, tivi thông minh,... thông qu	AVAILABLE	2025-02-05 15:17:33.718868	2025-02-05 15:17:33.718885
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, username, password, role, created_at, updated_at) FROM stdin;
8dc6a71f-5188-4486-bb01-b985858a86e4	1231231231231	123123213	USER	2025-02-05 15:17:04.838933	2025-02-05 15:17:04.838979
3c330791-9251-4c1a-b5c7-84b4e8ec48e7	Kienrawerawer	123123213	USER	2025-02-05 16:05:49.556016	2025-02-05 16:05:49.556052
79a4869f-fe4e-41ce-905c-a059feb60cf5	qweqwe	123123213	USER	2025-02-10 11:22:18.881707	2025-02-10 11:22:18.881743
e43f5148-1f82-4186-9345-ec158a41593f	qweqwqweqwee	1231	USER	2025-02-10 11:22:28.006154	2025-02-10 11:22:28.00617
c252ea67-98a5-45f2-9dd9-c080e379a44b	Kiernewrwe	qweKienr213@	USER	2025-02-10 14:17:47.961375	2025-02-10 14:17:47.961408
eacdaa5a-10f7-4653-89ef-79e8423397f6	Kiernewrwe123123	qweKienr213@	USER	2025-02-10 15:01:10.561075	2025-02-10 15:01:10.56111
07592411-b161-4343-b26f-62b81798597a	Kienewrw31	$2a$06$b4cKI25LKaTiUdUdbOS1zudMxQ5xC11llhle4xc/mJRbC6CeZOzsW	USER	2025-02-10 15:41:23.384025	2025-02-10 15:41:23.38406
adf08ce7-763e-4ed2-adc7-cd4d580cce8d	Kienewrw331	$2a$06$0Tfh4KwbZrhn.SlFwFtMlO9XonveOa0UImjCF0DawYQEiZ0scdUcK	USER	2025-02-10 16:55:08.412426	2025-02-10 16:55:08.412458
\.


--
-- Data for Name: user_session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_session (session_id, user_id, token, created_at, expires_at, revoked) FROM stdin;
\.


--
-- Name: user_session_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_session_seq', 1, false);


--
-- Name: assignment assignment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT assignment_pkey PRIMARY KEY (assignment_id);


--
-- Name: device device_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (device_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: user_session user_session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT user_session_pkey PRIMARY KEY (session_id);


--
-- Name: user_session user_session_token_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT user_session_token_key UNIQUE (token);


--
-- Name: user user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_username_key UNIQUE (username);


--
-- Name: assignment assignment_device_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT assignment_device_id_fkey FOREIGN KEY (device_id) REFERENCES public.device(device_id) ON DELETE CASCADE;


--
-- Name: assignment assignment_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT assignment_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON DELETE CASCADE;


--
-- Name: user_session user_session_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT user_session_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON DELETE CASCADE;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

