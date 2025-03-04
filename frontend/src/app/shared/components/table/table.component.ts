import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, EventEmitter, inject, Input, Output, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CellAction } from '../../model/cellaction';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements AfterViewInit {
  @Input() displayedColumns: string[] = [];
  @Input() dataSource: MatTableDataSource<any>;
  @Input() cellActions?: CellAction [] = [];

  @Output() onAction = new EventEmitter<{id: string, nameAction: string}>();
  @Output() search = new EventEmitter<string>();
  @Output() add = new EventEmitter<void>();
  @Output() refresh = new EventEmitter<void>();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  onSearch(event: any) {
    console.log("Table::",event.target.value);
    this.search.emit(event.target.value);
  }

  onAdd() {
    this.add.emit();
  }

  onRefresh() {
    this.refresh.emit();
  }

  onActionClick(id: string, nameAction: string) {
    console.log("Table::",id, nameAction);
    this.onAction.emit({id, nameAction});
  }

  get displayedColumnsWithoutLast(): string[] {
    return this.displayedColumns.slice(0, -1);
  }
  


}
