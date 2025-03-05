import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, EventEmitter, inject, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CellAction } from '../../model/cellaction';
import { Pageable } from '../../model/pageable';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements AfterViewInit, OnChanges {
  pageable: Pageable = {pageNumber: 1, pageSize: 20 , property: '', direction: ''};

  @Input() totalItems: number = 0;
  @Input() displayedColumns: string[] = [];
  @Input() dataSource: MatTableDataSource<any>;
  @Input() cellActions?: CellAction [] = [];

  @Output() pageableChange = new EventEmitter<Pageable>();
  @Output() onAction = new EventEmitter<{id: string, nameAction: string}>();
  @Output() search = new EventEmitter<string>();
  @Output() add = new EventEmitter<void>();
  @Output() refresh = new EventEmitter<void>();

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    console.log("table init::")
    this.pageableChange.emit(this.pageable)
    this.dataSource.sort = this.sort;
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['totalItems']) {
        console.log("total change::", this.totalItems)
    }
  }

  onSearch(event: any) {
    this.search.emit(event.target.value);
  }

  onAdd() {
    this.add.emit();
  }

  onRefresh() {
    this.refresh.emit();
  }

  onActionClick(id: string, nameAction: string) {
    this.onAction.emit({id, nameAction});
  }

  get displayedColumnsWithoutLast(): string[] {
    return this.displayedColumns.slice(0, -1);
  }

  onPageChange(event: any) {
    console.log("Page change:", event);
    this.pageable.pageNumber = event.pageIndex + 1;
    this.pageable.pageSize = event.pageSize;
    this.pageableChange.emit(this.pageable);
  }
  


}
