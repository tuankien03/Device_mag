--
-- PostgreSQL database dump
--

-- Dumped from database version 15.10 (Postgres.app)
-- Dumped by pg_dump version 15.10 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: assignment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assignment (
    assignment_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
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
    device_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    status character varying(20) DEFAULT 'AVAILABLE'::character varying NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT device_status_check CHECK (((status)::text = ANY ((ARRAY['AVAILABLE'::character varying, 'ASSIGNED'::character varying, 'RETURNING'::character varying])::text[])))
);


ALTER TABLE public.device OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO postgres;

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
     JOIN public.users u ON ((u.user_id = a.user_id)))
     JOIN public.device d ON ((d.device_id = a.device_id)));


ALTER TABLE public.user_device OWNER TO postgres;

--
-- Name: user_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_session (
    session_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    user_id uuid NOT NULL,
    token character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    expires_at timestamp without time zone,
    revoked boolean DEFAULT false NOT NULL
);


ALTER TABLE public.user_session OWNER TO postgres;

--
-- Data for Name: assignment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.assignment (assignment_id, user_id, device_id, assigned_at, returned_at) FROM stdin;
\.


--
-- Data for Name: device; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.device (device_id, name, description, status, created_at, updated_at) FROM stdin;
a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11	Máy chiếu	Máy chiếu văn phòng	AVAILABLE	2025-01-13 10:37:29.425184	2025-01-13 10:37:29.425184
d1e1a1b1-1a1b-1c1d-1e1f-1a1b1c1d1e1f	Máy chiếu	Máy chiếu văn phòng	AVAILABLE	2025-01-13 10:38:48.36513	2025-01-13 10:38:48.36513
d2e2a2b2-2a2b-2c2d-2e2f-2a2b2c2d2e2f	Máy in	Máy in đa năng	AVAILABLE	2025-01-13 10:38:48.36513	2025-01-13 10:38:48.36513
d3e3a3b3-3a3b-3c3d-3e3f-3a3b3c3d3e3f	Điều hòa	Điều hòa nhiệt độ	AVAILABLE	2025-01-13 10:38:48.36513	2025-01-13 10:38:48.36513
d4e4a4b4-4a4b-4c4d-4e4f-4a4b4c4d4e4f	Camera	Camera an ninh	AVAILABLE	2025-01-13 10:38:48.36513	2025-01-13 10:38:48.36513
d5e5a5b5-5a5b-5c5d-5e5f-5a5b5c5d5e5f	Router	Router Wi-Fi	AVAILABLE	2025-01-13 10:38:48.36513	2025-01-13 10:38:48.36513
\.


--
-- Data for Name: user_session; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_session (session_id, user_id, token, created_at, expires_at, revoked) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, username, password, role, created_at, updated_at) FROM stdin;
87f114c8-8291-4170-a691-5bb054cd1f59	Nguyễn Văn A	password1	ADMIN	2025-01-13 10:40:36.803741	2025-01-13 10:40:36.803741
3ae3530b-b2a4-451a-aaac-b035402a242c	Trần Thị B	password2	USER	2025-01-13 10:40:36.803741	2025-01-13 10:40:36.803741
4ffcb899-6d19-4e70-b038-78de8539fdf1	Lê Văn C	password3	USER	2025-01-13 10:40:36.803741	2025-01-13 10:40:36.803741
f4ec7191-fdb3-40d6-9f7f-e3571988e6ec	Phạm Thị D	password4	USER	2025-01-13 10:40:36.803741	2025-01-13 10:40:36.803741
1ebcf122-dbc1-4b20-a05d-ba0095174d97	Hoàng Văn E	password5	USER	2025-01-13 10:40:36.803741	2025-01-13 10:40:36.803741
\.


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
-- Name: users user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: user_session user_session_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT user_session_pkey PRIMARY KEY (session_id);


--
-- Name: users user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_username_key UNIQUE (username);


--
-- Name: assignment fk_assignment_device; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT fk_assignment_device FOREIGN KEY (device_id) REFERENCES public.device(device_id) ON DELETE CASCADE;


--
-- Name: assignment fk_assignment_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT fk_assignment_user FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- Name: user_session fk_session_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT fk_session_user FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

