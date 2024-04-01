
    create sequence categoria_seq start with 1 increment by 1;

    create sequence produto_seq start with 1 increment by 1;

    create table tb_categoria (
        id number(19,0) not null,
        nome varchar2(150 char) not null,
        primary key (id)
    );

    create table tb_produto (
        valor float(53) not null,
        categoria_id number(19,0) not null,
        id number(19,0) not null,
        nome varchar2(150 char) not null,
        descricao varchar2(255 char) not null,
        primary key (id)
    );

    alter table tb_produto 
       add constraint FK1ksfe2oumgjxke3oc5op41lej 
       foreign key (categoria_id) 
       references tb_categoria;
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Smartphone');
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Smart TV');
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Notebook');
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Tablet');
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Mouse');
INSERT INTO tb_categoria(id, nome) VALUES(categoria_seq.NEXTVAL, 'Teclado');
INSERT INTO tb_produto(id, nome, descricao, valor, categoria_id) VALUES(produto_seq.NEXTVAL, 'Mouse Microsoft', 'Mouse sem fio', 250.0, 5);
INSERT INTO tb_produto(id, nome, descricao, valor, categoria_id) VALUES(produto_seq.NEXTVAL, 'Smartphone Samsung Galaxy A54 5G', 'Samsung Galaxy A54 5G', 1799.0, 1);
INSERT INTO tb_produto(id, nome, descricao, valor, categoria_id) VALUES(produto_seq.NEXTVAL, 'Smart TV', 'Smart TV LG LED 65 polegadas', 3999, 2);
